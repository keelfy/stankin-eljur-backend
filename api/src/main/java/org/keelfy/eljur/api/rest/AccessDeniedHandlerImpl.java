package org.keelfy.eljur.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.LinkedHashMap;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@RequiredArgsConstructor
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        response.resetBuffer();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        final var payload = new LinkedHashMap<String, Object>();
        payload.put("timestamp", OffsetDateTime.now());
        payload.put("status", HttpStatus.FORBIDDEN.value());
        payload.put("error", HttpStatus.FORBIDDEN.getReasonPhrase());
        payload.put("message", "Authorization failed, not enough rights");
        payload.put("path", request.getRequestURI());

        objectMapper.writeValue(response.getOutputStream(), payload);
    }

}
