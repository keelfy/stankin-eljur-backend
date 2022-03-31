package org.keelfy.eljur.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Egor Kuzmin
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CredentialsNotFoundException extends UsernameNotFoundException {

    public CredentialsNotFoundException(String msg) {
        super(msg);
    }

}
