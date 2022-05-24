package org.keelfy.eljur.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.request.CreateSemesterRequest;
import org.keelfy.eljur.api.model.response.SemesterResponse;
import org.keelfy.eljur.api.service.SemesterService;
import org.keelfy.eljur.api.util.Authority;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@RestController
@RequiredArgsConstructor
public class SemesterController implements RootController {

    private static final String CONTROLLER_MAPPING = API_V1_MAPPING + "/semester";

    private static final String SEMESTERS_MAPPING = CONTROLLER_MAPPING + "s";

    private static final String SEMESTER_ORDINAL = "ordinal";

    private static final String SEMESTER_ID = "semesterId";

    private static final String BY_SEMESTER_ORDINAL = "/{" + SEMESTER_ORDINAL + "}";

    private static final String BY_SEMESTER_ID = "/{" + SEMESTER_ID + "}";

    private static final String SEMESTER_BY_ORDINAL_MAPPING = CONTROLLER_MAPPING + "/ordinal" + BY_SEMESTER_ORDINAL;

    private static final String SEMESTER_BY_ID_AND_ORDINAL_MAPPING = SEMESTERS_MAPPING + BY_SEMESTER_ID + "/ordinal" + BY_SEMESTER_ORDINAL;

    private static final String CREATE_SEMESTER_MAPPING = CONTROLLER_MAPPING + "/create";

    private final SemesterService semesterService;

    @PreAuthorize(Authority.CHECK_STUDENT)
    @GetMapping(path = CONTROLLER_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public SemesterResponse getCurrentSemester() {
        return semesterService.getSemester();
    }

    @PreAuthorize(Authority.CHECK_STUDENT)
    @GetMapping(path = SEMESTER_BY_ORDINAL_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public SemesterResponse getSemesterByOrdinal(@PathVariable(SEMESTER_ORDINAL) Integer ordinal) {
        return semesterService.getSemesterByOrdinal(ordinal);
    }

    @PreAuthorize(Authority.CHECK_STUDENT)
    @GetMapping(path = SEMESTERS_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SemesterResponse> getSemesters() {
        return semesterService.getSemesters();
    }

    @GetMapping(path = SEMESTER_BY_ID_AND_ORDINAL_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public SemesterResponse getSemesterByIdAndOrdinal(@PathVariable(SEMESTER_ID) Long semesterId,
                                                      @PathVariable(SEMESTER_ORDINAL) Integer ordinal) {

        return semesterService.getSemesterByIdAndOrdinal(semesterId, ordinal);
    }

    @PutMapping(path = CREATE_SEMESTER_MAPPING,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SemesterResponse createSemesterMapping(@Validated @RequestBody CreateSemesterRequest request) {
        return semesterService.createSemester(request);
    }

}
