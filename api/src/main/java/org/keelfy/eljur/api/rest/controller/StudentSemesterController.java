package org.keelfy.eljur.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.StudentSemesterResponse;
import org.keelfy.eljur.api.service.StudentSemesterService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@RestController
@RequiredArgsConstructor
public class StudentSemesterController implements RootController {

    private static final String CONTROLLER_MAPPING = API_V1_MAPPING + "/studentSemester";

    private static final String SEMESTERS_MAPPING = CONTROLLER_MAPPING + "s";

    private static final String SEMESTER_MAPPING = CONTROLLER_MAPPING;

    private static final String SEMESTER_ID = "semesterId";

    private static final String BY_SEMESTER_ID = "{" + SEMESTER_ID + "}";

    private static final String SEMESTER_BY_ID_MAPPING = CONTROLLER_MAPPING + "/" + BY_SEMESTER_ID;

    private final StudentSemesterService studentSemesterService;

    @GetMapping(path = SEMESTERS_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Long> getStudentSemesters() {
        return studentSemesterService.getStudentSemesters();
    }

    @GetMapping(path = SEMESTER_BY_ID_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentSemesterResponse getStudentSemester(@PathVariable(SEMESTER_ID) Long semesterId) {
        return studentSemesterService.getStudentSemester(semesterId);
    }

    @GetMapping(path = SEMESTER_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentSemesterResponse getStudentSemester() {
        return studentSemesterService.getStudentSemester();
    }

}
