package org.keelfy.eljur.api.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class StudentResponse {

    private Long id;

    private String email;

    private String firstName;

    private String secondName;

    private String middleName;

    private GroupResponse group;

}
