package org.keelfy.eljur.api.model.request;

import lombok.Data;
import org.keelfy.eljur.data.model.FinalExaminationType;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
public class CreateSemesterSubjectsRequest {

    @NotNull
    private Long semesterId;

    @NotEmpty
    private List<SemesterSubjectData> semesterSubjects;

    @Data
    @Validated
    public static class SemesterSubjectData {

        @Min(1)
        private Long subjectId;

        private Long teacherId;

        private FinalExaminationType finalExaminationType = FinalExaminationType.NONE;

        private Boolean courseWork = false;

        private Boolean firstModule = false;

        private Boolean secondModule = false;

        private BigDecimal coefficient;

    }

}
