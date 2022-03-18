package org.keelfy.eljur.service;

import org.keelfy.eljur.data.StudentSemester;
import org.springframework.lang.NonNull;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Egor Kuzmin
 */
public interface StudentSemesterService {

    @NonNull
    StudentSemester getStudentSemester(@NonNull BigInteger id);

    @NonNull
    List<BigInteger> getStudentSemesters();

}
