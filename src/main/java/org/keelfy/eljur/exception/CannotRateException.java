package org.keelfy.eljur.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Egor Kuzmin
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotRateException extends RuntimeException {

    public CannotRateException(String message) {
        super(message);
    }

}
