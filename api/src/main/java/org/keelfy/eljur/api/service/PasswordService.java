package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.configuration.property.CredentialsProperties;
import org.keelfy.eljur.api.exception.BadPasswordException;
import org.keelfy.eljur.api.model.TokenClaimType;
import org.keelfy.eljur.api.model.request.ChangeForgottenPasswordRequest;
import org.keelfy.eljur.api.model.request.ChangePasswordRequest;
import org.keelfy.eljur.api.model.request.ForgotPasswordRequest;
import org.keelfy.eljur.data.entity.Credentials;
import org.keelfy.eljur.data.repository.CredentialsRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Service
@RequiredArgsConstructor
public class PasswordService {

    private final CredentialsService credentialsService;

    private final CredentialsRepository credentialsRepository;

    private final CredentialsProperties credentialsProperties;

    private final MailSenderService mailSenderService;

    private final SecurityService securityService;

    private final PasswordEncoder passwordEncoder;

    public void resetPassword(ForgotPasswordRequest request) {
        final var username = request.getUsername();
        resetPassword(username);
    }

    public void changeForgottenPassword(ChangeForgottenPasswordRequest request) {
        final var token = request.getToken();
        final var newPassword = request.getPassword();
        changeForgottenPassword(token, newPassword);
    }

    public void changePassword(ChangePasswordRequest request) {
        final var username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final var credentials = credentialsService.getCredentialsByUsername(username);
        final var newPassword = request.getPassword();
        changePassword(credentials.getId(), newPassword);
    }

    public void resetPassword(String username) {
        final var credentials = credentialsService.getCredentialsByUsername(username);
        final var resetToken = securityService.createResetPasswordToken(credentials);
        credentials.setResetPasswordToken(resetToken);
        credentialsRepository.saveAndFlush(credentials);
        final var properties = credentialsProperties.getPassword().getForgot().getMail();
        mailSenderService.sendWithRetry(username, properties, Map.of(
                properties.getResetUrlVariableName(), String.format(properties.getResetUrl(), resetToken)
        ));
    }

    public void changeForgottenPassword(String token, String password) {
        final var parsedToken = securityService.parseTokenFromRequest(token);
        final var username = (String) parsedToken.get(TokenClaimType.USERNAME);
        final var credentials = credentialsService.getCredentialsByUsername(username);

        if (!token.equals(credentials.getResetPasswordToken())) {
            throw new IllegalArgumentException("Token to reset password is outdated");
        }

        changePassword(credentials, password);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void changePassword(Long credentialsId, String newPassword) {
        final var credentials = credentialsService.getCredentialsById(credentialsId);
        changePassword(credentials, newPassword);
    }

    public void changePassword(Credentials credentials, String newPassword) {
        final var encodedPassword = passwordEncoder.encode(newPassword);
        validatePassword(credentials.getPassword(), encodedPassword, newPassword);
        updatePassword(credentials, encodedPassword);
    }

    private Credentials updatePassword(Credentials credentials, String encodedPassword) {
        return credentialsRepository.saveAndFlush(credentials.setPassword(encodedPassword));
    }

    private void validatePassword(String currentPassword, String encodedPassword, String password) {
        final var properties = credentialsProperties.getPassword();

        if (!password.matches(properties.getValidationRegexp())) {
            throw new BadPasswordException("Password must contain at least one digit, "
                    + "one lower-case character, one upper-case character, one special symbol "
                    + "and not contain whitespaces");
        } else if (password.length() < properties.getMinLength()) {
            throw new BadPasswordException("Password cannot be less than "
                    + properties.getMinLength() + " characters");
        } else if (password.length() > properties.getMaxLength()) {
            throw new BadPasswordException("Password cannot be more than "
                    + properties.getMaxLength() + " characters");
        } else if (encodedPassword.equals(currentPassword)) {
            throw new BadPasswordException("Password cannot be the same with current password");
        }
    }

}
