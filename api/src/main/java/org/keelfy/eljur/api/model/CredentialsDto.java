package org.keelfy.eljur.api.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class CredentialsDto {

    @Min(0)
    @NotNull
    private Long id;

    @Email
    private String email;

    private String username;

    private String firstName;

    private String secondName;

    private String middleName;

    private String phoneNumber;

    private String password;

    private Boolean locked;

    private Boolean enabled;

    private Collection<String> roles = new ArrayList<>();

}
