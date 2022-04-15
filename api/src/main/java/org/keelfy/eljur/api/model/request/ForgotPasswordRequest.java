package org.keelfy.eljur.api.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class ForgotPasswordRequest {

    @Email
    @NotEmpty
    private String username;

}
