package org.keelfy.eljur.api.model.request;

import lombok.Data;
import org.keelfy.eljur.data.model.GradeType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
public class RateStudentRequest {

    @NonNull
    private Long studentSemesterId;

    @NonNull
    private Integer value;

    @NonNull
    private Long ratedBy;

    @NonNull
    private GradeType gradeType;

    @Nullable
    private Boolean onTime;

}
