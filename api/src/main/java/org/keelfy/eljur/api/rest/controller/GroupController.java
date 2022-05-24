package org.keelfy.eljur.api.rest.controller;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.request.CreateGroupRequest;
import org.keelfy.eljur.api.model.request.EditGroupRequest;
import org.keelfy.eljur.api.model.response.GradeResponse;
import org.keelfy.eljur.api.model.response.GroupResponse;
import org.keelfy.eljur.api.model.response.SemesterResponse;
import org.keelfy.eljur.api.model.response.StudentResponse;
import org.keelfy.eljur.api.service.GradeService;
import org.keelfy.eljur.api.service.GroupService;
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
public class GroupController implements RootController {

    private static final String CONTROLLER_MAPPING = API_V1_MAPPING + "/group";

    private static final String GROUPS_MAPPING = CONTROLLER_MAPPING + "s";

    private static final String ACTIVE_GROUPS_MAPPING = GROUPS_MAPPING + "/active";

    private static final String GROUP_ID = "groupId";

    private static final String BY_GROUP_ID = "/{" + GROUP_ID + "}";

    private static final String GROUP_BY_ID_MAPPING = CONTROLLER_MAPPING + BY_GROUP_ID;

    private static final String GROUP_SEMESTER_MAPPING = GROUP_BY_ID_MAPPING + "/semester";

    private static final String GROUP_SEMESTERS_MAPPING = GROUP_BY_ID_MAPPING + "/semesters";

    private static final String SEMESTER_ORDINAL = "ordinal";

    private static final String BY_SEMESTER_ORDINAL = "/{" + SEMESTER_ORDINAL + "}";

    private static final String GROUP_SEMESTER_BY_ORDINAL_MAPPING = GROUP_SEMESTER_MAPPING + BY_SEMESTER_ORDINAL;

    private static final String GROUP_STUDENTS_MAPPING = GROUP_BY_ID_MAPPING + "/students";

    private static final String SECOND_NAME_LIKE = "secondNameLike";

    private static final String BY_SECOND_NAME_LIKE = "/{" + SECOND_NAME_LIKE + "}";

    private static final String SEARCH_GROUP_STUDENTS_MAPPING = GROUP_STUDENTS_MAPPING + "/search" + BY_SECOND_NAME_LIKE;

    private static final String GROUP_STUDENTS_GRADES_MAPPING = GROUP_STUDENTS_MAPPING + "/grades";

    private static final String CREATE_GROUP_MAPPING = GROUPS_MAPPING + "/create";

    private static final String EDIT_GROUP_MAPPING = GROUP_BY_ID_MAPPING + "/edit";

    private final GroupService groupService;

    private final StudentService studentService;

    private final GradeService gradeService;

    private final SemesterService semesterService;

    @GetMapping(path = ACTIVE_GROUPS_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GroupResponse> getAllActiveGroups() {
        return groupService.getActiveGroups();
    }

    @GetMapping(path = GROUP_BY_ID_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public GroupResponse getGroup(@PathVariable(GROUP_ID) Long id) {
        return groupService.getGroup(id);
    }

    @GetMapping(path = GROUP_STUDENTS_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentResponse> getGroupStudents(@PathVariable(GROUP_ID) Long groupId) {
        return studentService.getStudentsOfGroup(groupId);
    }

    @GetMapping(path = GROUP_STUDENTS_GRADES_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GradeResponse> getGroupStudentGrades(@PathVariable(GROUP_ID) Long groupId) {
        return gradeService.getGroupStudentGrades(groupId);
    }

    @GetMapping(path = GROUP_SEMESTER_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public SemesterResponse getGroupSemester(@PathVariable(GROUP_ID) Long groupId) {
        return semesterService.getCurrentGroupSemester(groupId);
    }

    @GetMapping(path = GROUP_SEMESTERS_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SemesterResponse> getGroupSemesters(@PathVariable(GROUP_ID) Long groupId) {
        return semesterService.getGroupSemesters(groupId);
    }

    @GetMapping(path = GROUP_SEMESTER_BY_ORDINAL_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public SemesterResponse getGroupSemesterByOrdinal(@PathVariable(GROUP_ID) Long groupId,
                                                      @PathVariable(SEMESTER_ORDINAL) Integer ordinal) {

        return semesterService.getGroupSemesterByOrdinal(groupId, ordinal);
    }

    @PostMapping(path = CREATE_GROUP_MAPPING,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupResponse createGroup(@Validated @RequestBody CreateGroupRequest request) {
        return groupService.createGroup(request);
    }

    @PostMapping(path = EDIT_GROUP_MAPPING,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupResponse editGroup(@PathVariable(GROUP_ID) Long groupId,
                                   @Validated @RequestBody EditGroupRequest request) {

        return groupService.editGroup(groupId, request);
    }

    @GetMapping(path = SEARCH_GROUP_STUDENTS_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentResponse> searchStudentsBySecondName(@PathVariable(GROUP_ID) Long groupId,
                                                            @PathVariable(SECOND_NAME_LIKE) String secondName) {

        return studentService.searchStudentsByGroupAndSecondName(groupId, secondName);
    }

}
