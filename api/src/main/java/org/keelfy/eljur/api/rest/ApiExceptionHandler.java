package org.keelfy.eljur.api.rest;

import com.google.i18n.phonenumbers.NumberParseException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.api.exception.DuplicatedEntityException;
import org.keelfy.eljur.api.model.RestErrorCode;
import org.keelfy.eljur.api.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    private ErrorResponse handle(Exception exception, RestErrorCode errorCode) {
        log.error("Error occurred: {}", exception.getMessage(), exception);
        return new ErrorResponse()
                .setCode(errorCode.getCode())
                .setMessage(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception exception) {
        return handle(exception, RestErrorCode.UNKNOWN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handle(BadCredentialsException exception) {
        return handle(exception, RestErrorCode.BAD_CREDENTIALS);
    }

    @ExceptionHandler(NumberParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(NumberParseException exception) {
        return handle(exception, RestErrorCode.NUMBER_PARSE);
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handle(JwtException exception) {
        return handle(exception, RestErrorCode.JWT);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handle(AuthenticationException exception) {
        return handle(exception, RestErrorCode.AUTHENTICATION);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(IllegalArgumentException exception) {
        return handle(exception, RestErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicatedEntityException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handle(DuplicatedEntityException exception) {
        return handle(exception, RestErrorCode.ENTITY_CONFLICT);
    }

}
