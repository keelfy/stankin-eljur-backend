package org.keelfy.eljur.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class NotAStudentException extends RuntimeException {

    public NotAStudentException(String message) {
        super(message);
    }

}
