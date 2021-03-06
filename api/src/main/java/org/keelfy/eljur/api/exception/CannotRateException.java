package org.keelfy.eljur.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotRateException extends RuntimeException {

    public CannotRateException(String message) {
        super(message);
    }

}
