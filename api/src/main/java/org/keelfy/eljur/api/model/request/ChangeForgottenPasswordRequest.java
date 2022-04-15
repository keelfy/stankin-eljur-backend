package org.keelfy.eljur.api.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class ChangeForgottenPasswordRequest {

    @NotEmpty
    private final String token;

    @NotEmpty
    private final String password;

}
