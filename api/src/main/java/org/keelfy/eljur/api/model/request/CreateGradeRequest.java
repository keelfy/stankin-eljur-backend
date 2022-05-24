package org.keelfy.eljur.api.model.request;

import lombok.Data;
import org.keelfy.eljur.data.model.GradeType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
public class CreateGradeRequest {

    @NotNull
    @Min(1)
    private Long ratedStudentId;

    private Long ratedById;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer value;

    @NotNull
    private Long semesterSubjectId;

    @NotNull
    private GradeType gradeType;

    @NotNull
    private Boolean onTime = true;

    @NotNull
    private Boolean deadlineExtended = false;

}
