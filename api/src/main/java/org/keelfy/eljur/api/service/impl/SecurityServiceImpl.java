package org.keelfy.eljur.api.service.impl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.api.configuration.property.CredentialsProperties;
import org.keelfy.eljur.api.configuration.property.SecurityProperties;
import org.keelfy.eljur.api.model.TokenClaimType;
import org.keelfy.eljur.api.model.response.RefreshTokenResponse;
import org.keelfy.eljur.api.service.CredentialsService;
import org.keelfy.eljur.api.service.SecurityService;
import org.keelfy.eljur.data.entity.Credentials;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public final class SecurityServiceImpl implements SecurityService {

    private static final Long MILLIS_MULTIPLIER = 60L * 1000L;

    private final SecurityProperties securityProperties;

    private final CredentialsProperties credentialsProperties;

    private final CredentialsService credentialsService;

    @Override
    public Map<TokenClaimType, Object> parseTokenFromRequest(String token) {
        final var claims = Jwts.parser()
                .setSigningKey(getSecret())
                .parseClaimsJws(token.replace(securityProperties.getTokenPrefix(), ""));
        return Map.of(
                TokenClaimType.USERNAME, claims.getBody().getSubject(),
                TokenClaimType.ROLES, claims.getBody().get(TokenClaimType.ROLES.toString()),
                TokenClaimType.USER_ID, claims.getBody().get(TokenClaimType.USER_ID.toString())
        );
    }

    @Override
    public String createAccessToken(Credentials credentials) {
        final var roles = credentials.getLiteralAuthorities();
        final var expirationTime = securityProperties.getAccessTokenExpirationMinutes() * MILLIS_MULTIPLIER;
        return createToken(credentials, roles, expirationTime);
    }

    @Override
    public String createRefreshToken(Credentials credentials) {
        final var roles = credentials.getLiteralAuthorities();
        final var expirationTime = securityProperties.getRefreshTokenExpirationMinutes() * MILLIS_MULTIPLIER;
        return createToken(credentials, roles, expirationTime);
    }

    private String createToken(Credentials credentials, Collection<String> roles, long expirationTime) {
        return builder(credentials.getUsername(), expirationTime)
                .claim(TokenClaimType.ROLES.toString(), roles)
                .claim(TokenClaimType.USER_ID.toString(), credentials.getId())
                .compact();
    }

    @Override
    public String createResetPasswordToken(Credentials credentials) {
        final var properties = credentialsProperties.getPassword().getForgot();
        final var expirationTime = properties.getTokenExpirationMinutes() * MILLIS_MULTIPLIER;
        return builder(credentials.getEmail(), expirationTime)
                .claim(TokenClaimType.USER_ID.toString(), credentials.getId())
                .compact();
    }

    private JwtBuilder builder(String username, Long expirationTime) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, getSecret())
                .setHeaderParam(securityProperties.getTokenTypeHeader(), securityProperties.getTokenType())
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime));
    }

    private byte[] getSecret() {
        return securityProperties.getJwtSecret().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public RefreshTokenResponse createAccessByRefreshToken(String refreshToken) {
        checkRefreshToken(refreshToken);

        try {
            final var parsedRefreshToken = parseTokenFromRequest(refreshToken);
            final var username = (String) parsedRefreshToken.get(TokenClaimType.USERNAME);
            final var credentials = credentialsService.getCredentialsByUsername(username);
            final var newAccessToken = createAccessToken(credentials);
            final var newRefreshToken = createRefreshToken(credentials);
            return new RefreshTokenResponse(newAccessToken, newRefreshToken);
        } catch (Exception ex) {
            logTokenFailure(ex, refreshToken);
            throw ex;
        }
    }

    @Override
    public void logTokenFailure(Throwable throwable, String token) {
        final var builder = new StringBuilder();

        if (throwable instanceof ExpiredJwtException) {
            builder.append("Request to parse expired JWT : %s failed : %s");
        } else if (throwable instanceof UnsupportedJwtException) {
            builder.append("Request to parse unsupported JWT : %s failed : %s");
        } else if (throwable instanceof MalformedJwtException) {
            builder.append("Request to parse invalid JWT : %s failed : %s");
        } else if (throwable instanceof SignatureException) {
            builder.append("Request to parse JWT with invalid signature : %s failed : %s");
        } else if (throwable instanceof IllegalArgumentException) {
            builder.append("Request to parse empty or null JWT : %s failed : %s");
        } else {
            builder.append("Request to parse JWT failed. Token: %s : Error: %s");
        }

        final var message = String.format(builder.toString(), token, throwable.getMessage());
        log.debug(message);
    }

    @Override
    public void checkRefreshToken(String refreshToken) {
        if (!StringUtils.hasText(refreshToken)) {
            throw new IllegalArgumentException("Refresh token cannot be empty");
        }
    }

}
