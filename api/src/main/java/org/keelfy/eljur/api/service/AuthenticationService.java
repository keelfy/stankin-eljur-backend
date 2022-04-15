package org.keelfy.eljur.api.service;

import org.keelfy.eljur.api.security.PreAuthenticationChecker;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface AuthenticationService {

    AuthenticationProvider createAuthenticationProvider();

    default UserDetailsChecker createPreAuthenticationChecker() {
        return new PreAuthenticationChecker();
    }

}
