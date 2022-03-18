package org.keelfy.eljur.controller;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.data.StudentSemester;
import org.keelfy.eljur.service.StudentSemesterService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Egor Kuzmin
 */
@RestController
@RequiredArgsConstructor
public class StudentSemesterController implements BaseController {

    private static final String CONTROLLER_MAPPING = API_V1_MAPPING + "/semester/student";

    private static final String SEMESTERS_MAPPING = CONTROLLER_MAPPING;

    private static final String SEMESTER_ID = "semesterId";

    private static final String BY_SEMESTER_ID = "{" + SEMESTER_ID + "}";

    private static final String SEMESTER_MAPPING = SEMESTERS_MAPPING + "/" + BY_SEMESTER_ID;

    private final StudentSemesterService studentSemesterService;

    @GetMapping(
            path = SEMESTERS_MAPPING,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BigInteger> getStudentSemesters() {
        return studentSemesterService.getStudentSemesters();
    }

    @GetMapping(
            path = SEMESTER_MAPPING,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentSemester getStudentSemester(@PathVariable(SEMESTER_ID) BigInteger semesterId) {
        return studentSemesterService.getStudentSemester(semesterId);
    }

}
