package org.keelfy.eljur.api.model.request;

import lombok.Data;
import org.keelfy.eljur.data.model.GradeType;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.math.BigInteger;

/**
 * @author Egor Kuzmin
 */
@Data
public class RateStudentRequest {

    @NonNull
    private BigInteger studentSemesterId;

    @NonNull
    private Integer value;

    @Nullable
    private BigInteger ratedBy;

    @NonNull
    private GradeType gradeType;

    @Nullable
    private Boolean onTime;

}
