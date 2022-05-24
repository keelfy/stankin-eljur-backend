package org.keelfy.eljur.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.DepartmentResponse;
import org.keelfy.eljur.api.service.DepartmentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@RestController
@RequiredArgsConstructor
public class DepartmentController implements RootController {

    private static final String CONTROLLER_MAPPING = API_V1_MAPPING + "/departments";

    private final DepartmentService departmentService;

    @GetMapping(path = CONTROLLER_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DepartmentResponse> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

}
