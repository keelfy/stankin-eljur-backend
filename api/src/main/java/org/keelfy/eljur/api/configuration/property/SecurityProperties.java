package org.keelfy.eljur.api.configuration.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@ConfigurationProperties("app.security")
@SuppressWarnings("checkstyle:magicnumber")
public class SecurityProperties {

    private String jwtSecret;

    private String tokenType = "JWT";

    private String tokenTypeHeader = "Token-type";

    private String accessTokenHeader = "Authorization";

    private String tokenPrefix = "Bearer ";

    private String tokenExpiredAttributeName = "expired";

    private Long accessTokenExpirationMinutes = 1L;

    private Long refreshTokenExpirationMinutes = 60L;

}
