package org.keelfy.eljur.api.security;

import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.data.entity.Credentials;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
public class PreAuthenticationChecker implements UserDetailsChecker {

    @Override
    public void check(UserDetails user) {
        if (!(user instanceof Credentials)) {
            throw new IllegalArgumentException("System exception");
        }

        final var credentials = (Credentials) user;
        final var roles = credentials.getLiteralAuthorities();

        if (credentials.getLocked()) {
            log.debug("Account is locked: {}", credentials);
            throw new LockedException("Your account is locked");
        }

        if (!credentials.getEnabled()) {
            log.debug("User account is disabled");
            throw new DisabledException("User is disabled");
        }
    }

}