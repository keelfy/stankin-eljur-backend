package org.keelfy.eljur.api.model.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.keelfy.eljur.data.model.GradeType;

import java.math.BigDecimal;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class GradeResponse {

    private Long id;

    private Integer value;

    private GradeType gradeType;

    private Long semesterSubjectId;

    private Boolean onTime;

    private TeacherResponse ratedBy;

}
