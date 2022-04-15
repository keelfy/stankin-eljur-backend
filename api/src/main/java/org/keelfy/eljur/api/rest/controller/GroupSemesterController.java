package org.keelfy.eljur.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.GroupSemesterResponse;
import org.keelfy.eljur.api.service.GroupSemesterService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@RestController
@RequiredArgsConstructor
public class GroupSemesterController implements RootController {

    private static final String CONTROLLER_MAPPING = API_V1_MAPPING + "/groupSemester";

    private final GroupSemesterService groupSemesterService;

    @GetMapping(path = CONTROLLER_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public GroupSemesterResponse getCurrentGroupSemester() {
        return groupSemesterService.getGroupSemester();
    }

}
