package org.keelfy.eljur.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CredentialsNotFoundException extends UsernameNotFoundException {

    public CredentialsNotFoundException(String message) {
        super(message);
        log.error(message);
    }

}
