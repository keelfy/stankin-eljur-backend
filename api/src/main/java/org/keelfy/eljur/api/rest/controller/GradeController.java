package org.keelfy.eljur.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.request.CreateGradeRequest;
import org.keelfy.eljur.api.model.response.GradeResponse;
import org.keelfy.eljur.api.service.GradeService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@RestController
@RequiredArgsConstructor
public class GradeController implements RootController {

    private static final String CONTROLLER_MAPPING = API_V1_MAPPING + "/grades";

    private static final String CREATE_GRADE_MAPPING = CONTROLLER_MAPPING + "/create";

    private final GradeService gradeService;

    @GetMapping(path = CONTROLLER_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GradeResponse> getGrades() {
        return gradeService.getGrades();
    }

    @PutMapping(path = CREATE_GRADE_MAPPING,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GradeResponse rateStudent(@Validated @RequestBody CreateGradeRequest request) {
        return gradeService.rateStudent(request);
    }

}
