package org.keelfy.eljur.api.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.api.listener.event.AuthenticationSuccessEvent;
import org.keelfy.eljur.api.service.CredentialsService;
import org.keelfy.eljur.data.entity.Credentials;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    private final CredentialsService credentialsService;

    public void fireAuthenticationSuccess(Credentials credentials, ZonedDateTime authenticationTime) {
        log.info("Publishing authentication success event");
        final var event = new AuthenticationSuccessEvent(credentials, authenticationTime);
        eventPublisher.publishEvent(event);
    }

    @Bean
    ApplicationListener<AuthenticationSuccessEvent> authenticationSuccessListener() {
        return (e) -> {
            final var credentials = e.getCredentials();
            final var authenticationTime = e.getAuthenticationTime();
            credentialsService.updateLatestAuthTime(credentials, authenticationTime, true);
        };
    }

}
