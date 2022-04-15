package org.keelfy.eljur.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Yegor Kuzmin (keelfy)
 * */
@Slf4j
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadPasswordException extends RuntimeException {

    private static final long serialVersionUID = -613015155200497684L;

    public BadPasswordException(String message) {
        super(message);
        log.error(message);
    }

}
