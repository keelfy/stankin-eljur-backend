package org.keelfy.eljur.api.model.request;

import lombok.Data;
import org.keelfy.eljur.data.model.Degree;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
public class EditGroupRequest {

    private String name;

    private Long departmentId;

    private Boolean active;

    private Long headmanId;

    private Integer studyStartYear;

    private Degree degree;

}
