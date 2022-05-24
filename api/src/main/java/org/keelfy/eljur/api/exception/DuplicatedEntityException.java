package org.keelfy.eljur.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedEntityException extends RuntimeException {

    public DuplicatedEntityException(String message) {
        super(message);
    }

}
