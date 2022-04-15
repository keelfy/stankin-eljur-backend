package org.keelfy.eljur.api.service;

import org.keelfy.eljur.api.model.TokenClaimType;
import org.keelfy.eljur.api.model.response.RefreshTokenResponse;
import org.keelfy.eljur.data.entity.Credentials;

import java.util.Map;

/**
 * Сервис, отвечающий за создание и валидирование токенов.
 *
 * @author Yegor Kuzmin (keelfy)
 */
public interface SecurityService {

    /**
     * Валдация, парсинг и маппинг данных из токена.
     */
    Map<TokenClaimType, Object> parseTokenFromRequest(String token);

    /**
     * Создание токена доступа к сервису.
     */
    String createAccessToken(Credentials credentials);

    /**
     * Создание токена обновления.
     */
    String createRefreshToken(Credentials credentials);

    /**
     * Создание токена для сброса пароля.
     */
    String createResetPasswordToken(Credentials credentials);

    /**
     * Обновление токена доступа по токену обновления.
     */
    RefreshTokenResponse createAccessByRefreshToken(String refreshToken);

    /**
     * Валидация токена обновления.
     */
    void checkRefreshToken(String refreshToken);

    /**
     * Логирование исключения, полученного при работе с токеном.
     */
    void logTokenFailure(Throwable throwable, String token);

}
