package org.keelfy.eljur.api.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public class AuthenticationFailureBadCredentialsEvent extends ApplicationEvent {

    public AuthenticationFailureBadCredentialsEvent(String username) {
        super(username);
    }

    public String getUsername() {
        return (String) this.source;
    }

}
