package org.keelfy.eljur.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Yegor Kuzmin (keelfy)
 * */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvitationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -4597460566531071496L;

    public InvitationNotFoundException(String message) {
        super(message);
    }

    public InvitationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvitationNotFoundException(Throwable cause) {
        super(cause);
    }

}
