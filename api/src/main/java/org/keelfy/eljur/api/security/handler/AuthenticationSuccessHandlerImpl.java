package org.keelfy.eljur.api.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.api.configuration.property.SecurityProperties;
import org.keelfy.eljur.api.listener.SecurityEventPublisher;
import org.keelfy.eljur.api.service.CredentialsService;
import org.keelfy.eljur.api.service.SecurityService;
import org.keelfy.eljur.data.entity.Credentials;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    private final CredentialsService credentialsService;

    private final SecurityService jwtService;

    private final SecurityProperties securityProperties;

    private final SecurityEventPublisher securityEventPublisher;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        final var userDetails = (Credentials) authentication.getPrincipal();
        final var username = userDetails.getUsername();
        final var credentials = credentialsService.getCredentialsByUsername(username);

        securityEventPublisher.fireAuthenticationSuccess(credentials, ZonedDateTime.now());

        final var token = jwtService.createAccessToken(credentials);
        final var refreshToken = jwtService.createRefreshToken(credentials);
        final var tokens = Map.of(
                "accessToken", securityProperties.getTokenPrefix() + token,
                "refreshToken", securityProperties.getTokenPrefix() + refreshToken
        );

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), tokens);
    }

}
