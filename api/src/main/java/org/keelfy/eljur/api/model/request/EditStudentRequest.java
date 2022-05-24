package org.keelfy.eljur.api.model.request;

import lombok.Data;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
public class EditStudentRequest {

    private String firstName;

    private String secondName;

    private String middleName;

}
