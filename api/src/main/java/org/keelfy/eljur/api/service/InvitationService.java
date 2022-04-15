package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.api.configuration.property.InvitationProperties;
import org.keelfy.eljur.api.exception.InvitationException;
import org.keelfy.eljur.api.model.CredentialsDto;
import org.keelfy.eljur.api.model.request.CompleteInvitationRequest;
import org.keelfy.eljur.api.model.request.CreateInvitationRequest;
import org.keelfy.eljur.data.entity.Credentials;
import org.keelfy.eljur.data.entity.Invitation;
import org.keelfy.eljur.data.model.InvitationStatus;
import org.keelfy.eljur.data.repository.CredentialsRepository;
import org.keelfy.eljur.data.repository.InvitationRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InvitationService {

    private final MailSenderService mailSenderService;

    private final InvitationProperties invitationProperties;

    private final CredentialsService credentialsService;

    private final CredentialsRepository credentialsRepository;

    private final InvitationRepository invitationRepository;

    private final PasswordService passwordService;

    public void createInvitation(CreateInvitationRequest request) {
        final var email = request.getEmail();
        final var phoneNumber = request.getPhoneNumber();
        final var roles = request.getRoles();
        final var creatorUsername = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final var credentials = credentialsService.getCredentialsByUsername(creatorUsername);
        final var userDto = new CredentialsDto()
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setRoles(roles);
        createInvitation(userDto, credentials);
    }

    private void createInvitation(CredentialsDto credentialsDto, Credentials creator) {
        final var email = credentialsDto.getEmail();
        final var existedCredentials = credentialsRepository.findByEmail(email).orElse(null);

        if (existedCredentials != null) {
            invitationRepository.findByInvited(existedCredentials)
                    .stream()
                    .filter(this::isInvitationValid)
                    .map(invitation -> invitation.setStatus(InvitationStatus.BLOCKED))
                    .forEach(invitationRepository::save);

            if (existedCredentials.getEnabled()) {
                passwordService.resetPassword(email);
                return;
            }
        }

        final var invitedCredentials = Optional.ofNullable(existedCredentials)
                .orElse(credentialsService.createCredentials(credentialsDto));

        final var invitation = createNewInvitation(invitedCredentials, creator);
        sendConfirmationEmail(email, invitation.getInvitationId());
    }

    private Invitation createNewInvitation(Credentials invited, Credentials creator) {
        final var expirationTime = ZonedDateTime.now()
                .plusMinutes(invitationProperties.getExpiration().getExpirationMinutes());
        final var invitationId = UUID.randomUUID().toString();
        return invitationRepository.saveAndFlush(new Invitation()
                .setInvitationId(invitationId)
                .setInvited(invited)
                .setCreator(creator)
                .setValidUntil(expirationTime)
                .setStatus(InvitationStatus.WAITING));
    }

    private void sendConfirmationEmail(String email, String invitationCode) {
        final var properties = invitationProperties.getMail();
        mailSenderService.sendWithRetry(email, properties, Map.of(
                properties.getReceiveInvitationUrlVariableName(),
                String.format(properties.getReceiveInvitationUrl(), invitationCode)
        ));
    }

    public void completeInvitation(CompleteInvitationRequest request) {
        final var invitation = getInvitationByCode(request.getInvitationCode());
        final var credentials = invitation.getInvited();
        final var password = request.getPassword();
        passwordService.changePassword(credentials, password);
        credentialsService.enableCredentials(credentials);
    }

    public void expireInvitations() {
        invitationRepository.findByValidUntilBefore(ZonedDateTime.now())
                .stream()
                .map(invitation -> invitation.setStatus(InvitationStatus.EXPIRED))
                .forEach(invitationRepository::save);
    }

    public boolean isInvitationValid(Invitation invitation) {
        return invitation.getStatus() == InvitationStatus.WAITING
                && invitation.getValidUntil().compareTo(ZonedDateTime.now()) > 0;
    }

    public Invitation getInvitationByCode(String invitationId) {
        return invitationRepository.findByInvitationId(invitationId)
                .orElseThrow(() -> new InvitationException("Invitation id is invalid"));
    }

}
