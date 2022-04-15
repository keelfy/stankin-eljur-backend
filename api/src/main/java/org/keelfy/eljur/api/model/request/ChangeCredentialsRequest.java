package org.keelfy.eljur.api.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class ChangeCredentialsRequest {

    @Email
    private String email;

    private String username;

    private String phoneNumber;

}
