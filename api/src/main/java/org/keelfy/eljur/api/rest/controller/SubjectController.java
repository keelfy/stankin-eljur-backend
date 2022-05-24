package org.keelfy.eljur.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.request.CreateSubjectRequest;
import org.keelfy.eljur.api.model.request.EditSubjectRequest;
import org.keelfy.eljur.api.model.response.SubjectResponse;
import org.keelfy.eljur.api.service.SubjectService;
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
public class SubjectController implements RootController {

    private static final String CONTROLLER_MAPPING = API_V1_MAPPING + "/subject";

    private static final String LIST_MAPPING = CONTROLLER_MAPPING + "s";

    private static final String CREATE_MAPPING = CONTROLLER_MAPPING + "/create";

    private static final String SUBJECT_ID = "subjectId";

    private static final String BY_SUBJECT_ID = "/{" + SUBJECT_ID + "}";

    private static final String EDIT_MAPPING = CONTROLLER_MAPPING + BY_SUBJECT_ID + "/edit";

    private final SubjectService subjectService;

    @PutMapping(path = CREATE_MAPPING,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SubjectResponse createSubject(@Validated @RequestBody CreateSubjectRequest request) {
        return subjectService.createSubject(request);
    }

    @PostMapping(path = EDIT_MAPPING,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SubjectResponse editSubject(@PathVariable(SUBJECT_ID) Long subjectId,
                                       @Validated @RequestBody EditSubjectRequest request) {

        return subjectService.editSubject(subjectId, request);
    }

    @GetMapping(path = LIST_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubjectResponse> listSubjects() {
        return subjectService.listSubjects();
    }

}
