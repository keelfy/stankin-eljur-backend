package org.keelfy.eljur.api.listener.event;

import lombok.Getter;
import org.keelfy.eljur.data.entity.Credentials;
import org.springframework.context.ApplicationEvent;

import java.time.ZonedDateTime;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Getter
public final class AuthenticationSuccessEvent extends ApplicationEvent {

    private final ZonedDateTime authenticationTime;

    public AuthenticationSuccessEvent(Credentials credentials, ZonedDateTime authTime) {
        super(credentials);
        this.authenticationTime = authTime;
    }

    public Credentials getCredentials() {
        return (Credentials) this.source;
    }

}
