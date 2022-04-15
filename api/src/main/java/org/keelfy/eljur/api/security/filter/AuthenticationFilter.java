package org.keelfy.eljur.api.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.api.exception.CredentialsNotFoundException;
import org.keelfy.eljur.api.model.CredentialsDto;
import org.keelfy.eljur.api.service.CredentialsService;
import org.keelfy.eljur.api.util.RestConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final CredentialsService credentialsService;

    private final ObjectMapper mapper;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                CredentialsService credentialsService,
                                AuthenticationSuccessHandler successHandler,
                                AuthenticationFailureHandler failureHandler,
                                ObjectMapper objectMapper) {

        this.setAuthenticationManager(authenticationManager);
        this.setFilterProcessesUrl(RestConstants.AUTH_LOGIN_URL);
        this.setAuthenticationSuccessHandler(successHandler);
        this.setAuthenticationFailureHandler(failureHandler);

        this.credentialsService = credentialsService;
        this.mapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            log.info(request.getHeaderNames() + " ");
            final var credentials = mapper.readValue(request.getInputStream(), CredentialsDto.class);
            final var username = credentials.getUsername();
            final var user = credentialsService.loadUserByUsername(username);
            final var token = new UsernamePasswordAuthenticationToken(
                    credentials.getUsername(),
                    credentials.getPassword(),
                    user.getAuthorities()
            );
            return getAuthenticationManager().authenticate(token);
        } catch (CredentialsNotFoundException ex) {
            throw new BadCredentialsException(ex.getMessage(), ex);
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

}
