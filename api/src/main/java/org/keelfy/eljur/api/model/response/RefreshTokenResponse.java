package org.keelfy.eljur.api.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@RequiredArgsConstructor
public class RefreshTokenResponse {

    private final String accessToken;

    private final String refreshToken;

}
