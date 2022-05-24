package org.keelfy.eljur.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.request.CreateSemesterSubjectsRequest;
import org.keelfy.eljur.api.model.response.SemesterSubjectResponse;
import org.keelfy.eljur.api.service.SemesterSubjectService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@RestController
@RequiredArgsConstructor
public class SemesterSubjectController implements RootController {

    private static final String CONTROLLER_MAPPING = API_V1_MAPPING + "/semesterSubject";

    private static final String SEMESTER_SUBJECTS_MAPPING = CONTROLLER_MAPPING + "s";

    private static final String CREATE_SEMESTER_SUBJECTS_MAPPING = SEMESTER_SUBJECTS_MAPPING + "/create";

    private final SemesterSubjectService semesterSubjectService;

    @PutMapping(path = CREATE_SEMESTER_SUBJECTS_MAPPING,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<SemesterSubjectResponse> createSemesterSubjects(@Validated
                                                                @RequestBody CreateSemesterSubjectsRequest request) {

        return semesterSubjectService.createSemesterSubjects(request);
    }



}
