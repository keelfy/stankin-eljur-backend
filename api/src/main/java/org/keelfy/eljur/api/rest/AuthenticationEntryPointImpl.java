package org.keelfy.eljur.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.RestErrorCode;
import org.keelfy.eljur.api.model.response.ErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@ControllerAdvice
@RequiredArgsConstructor
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException exception) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        final var error = new ErrorResponse()
                .setCode(extractCode(exception))
                .setMessage(exception.getMessage());
        mapper.writeValue(response.getOutputStream(), error);
    }

    private Integer extractCode(AuthenticationException exception) {
        if (exception instanceof BadCredentialsException) {
            return RestErrorCode.AUTHENTICATION.getCode();
        } else {
            return RestErrorCode.UNKNOWN.getCode();
        }
    }

}
