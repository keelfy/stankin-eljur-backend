package org.keelfy.eljur.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@ResponseStatus(HttpStatus.FORBIDDEN)
public class CredentialsLockedException extends RuntimeException {

    public CredentialsLockedException(String message) {
        super(message);
        log.error(message);
    }
}
