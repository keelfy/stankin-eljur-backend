package org.keelfy.eljur.api.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class CreateCredentialsRequest {

    @NotNull
    private String username;

    private String email;

    private String phoneNumber;

    @NotNull
    private String firstName;

    @NotNull
    private String secondName;

    private String middleName;

    private Long groupId;

    private Long departmentId;

    private String studentTicketNumber;

    private String recordBookNumber;

    @NotNull
    private String role;

}
