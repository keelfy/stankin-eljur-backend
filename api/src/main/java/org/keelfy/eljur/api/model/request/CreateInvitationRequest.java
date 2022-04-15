package org.keelfy.eljur.api.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class CreateInvitationRequest {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String phoneNumber;

    private List<String> roles = new ArrayList<>();

}
