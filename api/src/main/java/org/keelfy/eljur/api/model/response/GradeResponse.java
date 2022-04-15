package org.keelfy.eljur.api.model.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.keelfy.eljur.data.model.GradeType;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class GradeResponse {

    private Long id;

    private Integer value;

    private GradeType gradeType;

    private Long subjectId;

    private Boolean onTime;

    private String ratedByFullName;

    private Long ratedById;

}
