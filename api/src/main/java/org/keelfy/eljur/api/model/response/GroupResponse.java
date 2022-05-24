package org.keelfy.eljur.api.model.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.keelfy.eljur.data.model.Degree;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class GroupResponse {

    private Long id;

    private String name;

    private Boolean active;

    private Degree degree;

    private DepartmentResponse department;

    private StudentResponse headman;

    private Integer studyStartYear;

}
