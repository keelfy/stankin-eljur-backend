package org.keelfy.eljur.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.request.EditStudentRequest;
import org.keelfy.eljur.api.model.response.GradeResponse;
import org.keelfy.eljur.api.model.response.SemesterResponse;
import org.keelfy.eljur.api.model.response.StudentResponse;
import org.keelfy.eljur.api.service.GradeService;
import org.keelfy.eljur.api.service.SemesterService;
import org.keelfy.eljur.api.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@RestController
@RequiredArgsConstructor
public class StudentController implements RootController {

    private static final String CONTROLLER_MAPPING = API_V1_MAPPING + "/student";

    private static final String STUDENTS_MAPPING = CONTROLLER_MAPPING + "s";

    private static final String SECOND_NAME_LIKE = "secondNameLike";

    private static final String BY_SECOND_NAME_LIKE = "/{" + SECOND_NAME_LIKE + "}";

    private static final String SEARCH_BY_SECOND_NAME_MAPPING = STUDENTS_MAPPING + "/searchBySecondName" + BY_SECOND_NAME_LIKE;

    private static final String STUDENT_ID = "studentId";

    private static final String BY_STUDENT_ID = "/{" + STUDENT_ID + "}";

    private static final String STUDENT_BY_ID_MAPPING = CONTROLLER_MAPPING + BY_STUDENT_ID;

    private static final String STUDENT_GRADES_MAPPING = STUDENT_BY_ID_MAPPING + "/grades";

    private static final String EDIT_STUDENT_MAPPING = STUDENT_BY_ID_MAPPING + "/edit";

    private static final String STUDENT_CURRENT_SEMESTER = STUDENT_BY_ID_MAPPING + "/semester";

    private static final String SEMESTER_ORDINAL = "semesterOrdinal";

    private static final String BY_SEMESTER_ORDINAL = "/{" + SEMESTER_ORDINAL + "}";

    private static final String STUDENT_ORDINAL_SEMESTER = STUDENT_CURRENT_SEMESTER + "/ordinal" + BY_SEMESTER_ORDINAL;

    private final StudentService studentService;

    private final GradeService gradeService;

    private final SemesterService semesterService;

    @GetMapping(path = SEARCH_BY_SECOND_NAME_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentResponse> searchStudentsBySecondName(@PathVariable(SECOND_NAME_LIKE) String secondName) {
        return studentService.searchStudentsBySecondName(secondName);
    }

    @GetMapping(path = STUDENT_BY_ID_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentResponse studentById(@PathVariable(STUDENT_ID) Long studentId) {
        return studentService.studentById(studentId);
    }

    @GetMapping(path = STUDENT_GRADES_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GradeResponse> getGrades(@PathVariable(STUDENT_ID) Long studentId) {
        return gradeService.gradesByStudentId(studentId);
    }

    @GetMapping(path = STUDENT_CURRENT_SEMESTER, produces = MediaType.APPLICATION_JSON_VALUE)
    public SemesterResponse currentSemester(@PathVariable(STUDENT_ID) Long studentId) {
        return semesterService.getStudentSemester(studentId);
    }

    @GetMapping(path = STUDENT_ORDINAL_SEMESTER, produces = MediaType.APPLICATION_JSON_VALUE)
    public SemesterResponse semesterByOrdinal(@PathVariable(STUDENT_ID) Long studentId,
                                              @PathVariable(SEMESTER_ORDINAL) Integer semesterOrdinal) {

        return semesterService.getStudentSemesterByOrdinal(studentId, semesterOrdinal);
    }

    @PostMapping(path = EDIT_STUDENT_MAPPING,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentResponse editStudent(@PathVariable(STUDENT_ID) Long studentId,
                                       @Validated @RequestBody EditStudentRequest request) {

        return studentService.editStudent(studentId, request);
    }

}
