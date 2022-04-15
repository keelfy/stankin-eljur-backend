package org.keelfy.eljur.api.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class StudentSemesterResponse {

    private Long id;

    private List<GradeResponse> grades;

}
