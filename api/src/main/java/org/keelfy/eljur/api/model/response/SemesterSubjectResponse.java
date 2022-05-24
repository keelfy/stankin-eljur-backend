package org.keelfy.eljur.api.model.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.keelfy.eljur.data.model.FinalExaminationType;

import java.math.BigDecimal;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class SemesterSubjectResponse {

    private Long id;

    private SubjectResponse subject;

    private FinalExaminationType examinationType;

    private Boolean courseWork;

    private Boolean firstModule;

    private Boolean secondModule;

    private BigDecimal coefficient;

    private TeacherResponse teacher;

}
