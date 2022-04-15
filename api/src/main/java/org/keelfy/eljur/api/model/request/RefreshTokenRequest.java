package org.keelfy.eljur.api.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
public class RefreshTokenRequest {

    @NotEmpty
    private String refreshToken;

}
