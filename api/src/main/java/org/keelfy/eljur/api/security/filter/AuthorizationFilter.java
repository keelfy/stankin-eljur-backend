package org.keelfy.eljur.api.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.api.configuration.property.SecurityProperties;
import org.keelfy.eljur.api.model.RestErrorCode;
import org.keelfy.eljur.api.model.TokenAuthenticationDetails;
import org.keelfy.eljur.api.model.TokenClaimType;
import org.keelfy.eljur.api.model.response.ErrorResponse;
import org.keelfy.eljur.api.service.CredentialsService;
import org.keelfy.eljur.api.service.SecurityService;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@Getter
@Setter
@Accessors(chain = true)
public class AuthorizationFilter extends BasicAuthenticationFilter {

    private SecurityService securityService;

    private CredentialsService credentialsService;

    private SecurityProperties securityProperties;

    private ObjectMapper objectMapper;

    public AuthorizationFilter(AuthenticationManager authenticationManager,
                               AuthenticationEntryPoint authenticationEntryPoint) {

        super(authenticationManager, authenticationEntryPoint);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        final var expired = request.getAttribute(securityProperties.getTokenExpiredAttributeName());

        if (expired != null) {
            sendError((String) expired, RestErrorCode.AUTHENTICATION, response);
            return;
        }

        try {
            Optional.ofNullable(getAuthentication(request, response))
                    .ifPresent(SecurityContextHolder.getContext()::setAuthentication);
        } catch (ExpiredJwtException ex) {
            final var exception = new CredentialsExpiredException("Access token expired");
            super.getAuthenticationEntryPoint().commence(request, response, exception);
        } catch (JwtException ex) {
            final var exception = new BadCredentialsException(ex.getMessage(), ex);
            super.getAuthenticationEntryPoint().commence(request, response, exception);
        }

        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        final var token = request.getHeader(securityProperties.getAccessTokenHeader());

        if (StringUtils.hasText(token) && token.startsWith(securityProperties.getTokenPrefix())) {
            try {
                final var parsedToken = securityService.parseTokenFromRequest(token);
                final var username = (String) parsedToken.get(TokenClaimType.USERNAME);

                if (StringUtils.hasText(username)) {
                    final var authorities = ((List<?>) parsedToken
                            .get(TokenClaimType.ROLES))
                            .stream()
                            .map(authority -> new SimpleGrantedAuthority((String) authority))
                            .collect(Collectors.toList());
                    final var credentials = credentialsService.getCredentialsByUsername(username);
                    final var authToken = new UsernamePasswordAuthenticationToken(username, credentials, authorities);
                    final var userId = (Integer) parsedToken.get(TokenClaimType.USER_ID);
                    final var details = new TokenAuthenticationDetails(userId.longValue());
                    authToken.setDetails(details);
                    return authToken;
                }
            } catch (Exception ex) {
                securityService.logTokenFailure(ex, token);
                addAttributeExpiredToken(ex, request, response);
                throw ex;
            }
        }
        return null;
    }

    @SneakyThrows
    private void addAttributeExpiredToken(Throwable cause, HttpServletRequest request, HttpServletResponse response) {
        if (cause instanceof ExpiredJwtException) {
            final var message = "Access token expired";
            request.setAttribute(securityProperties.getTokenExpiredAttributeName(), message);
            sendError(message, RestErrorCode.TOKEN_EXPIRED, response);
        } else if (cause instanceof JwtException || cause instanceof AuthenticationException) {
            final var message = Optional.ofNullable(cause.getMessage()).orElse("Unauthorized");
            sendError(message, RestErrorCode.AUTHENTICATION, response);
        }
    }

    @SneakyThrows
    private void sendError(String message, RestErrorCode errorCode, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getOutputStream(), new ErrorResponse()
                .setCode(errorCode.getCode())
                .setMessage(message));
    }

}

