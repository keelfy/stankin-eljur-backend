package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.converter.GroupToResponseConverter;
import org.keelfy.eljur.api.model.request.CreateGroupRequest;
import org.keelfy.eljur.api.model.request.EditGroupRequest;
import org.keelfy.eljur.api.model.response.GroupResponse;
import org.keelfy.eljur.api.util.Authority;
import org.keelfy.eljur.data.entity.Group;
import org.keelfy.eljur.data.repository.GroupRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    private final GroupToResponseConverter groupToResponseConverter;

    private final DepartmentService departmentService;

    private final CredentialsService credentialsService;

    @PreAuthorize(Authority.CHECK_ADMINISTRATION)
    public List<GroupResponse> getActiveGroups() {
        return groupRepository.findByActive(true)
                .stream()
                .map(groupToResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @PreAuthorize((Authority.CHECK_ADMINISTRATION))
    public GroupResponse getGroup(Long id) {
        return groupToResponseConverter.convert(getGroupById(id));
    }

    @NonNull
    @PreAuthorize(Authority.CHECK_ADMINISTRATION)
    public GroupResponse createGroup(@NonNull CreateGroupRequest request) {
        final var department = Optional.ofNullable(request.getDepartmentId())
                .map(departmentService::getDepartmentById)
                .orElse(null);
        final var headman = Optional.ofNullable(request.getHeadmanId())
                .map(credentialsService::getCredentialsById)
                .orElse(null);
        final var group = new Group()
                .setName(request.getName())
                .setActive(request.getActive())
                .setStudyStartYear(request.getStudyStartYear())
                .setDepartment(department)
                .setHeadman(headman)
                .setDegree(request.getDegree());
        return groupToResponseConverter.convert(groupRepository.saveAndFlush(group));
    }

    @NonNull
    @PreAuthorize(Authority.CHECK_ADMINISTRATION)
    public GroupResponse editGroup(@NonNull Long groupId, @NonNull EditGroupRequest request) {
        final var group = getGroupById(groupId);
        Optional.ofNullable(request.getDepartmentId())
                .map(departmentService::getDepartmentById)
                .ifPresent(group::setDepartment);
        Optional.ofNullable(request.getHeadmanId())
                .map(credentialsService::getCredentialsById)
                .ifPresent(group::setHeadman);
        Optional.ofNullable(request.getActive()).ifPresent(group::setActive);
        Optional.ofNullable(request.getName()).ifPresent(group::setName);
        Optional.ofNullable(request.getStudyStartYear()).ifPresent(group::setStudyStartYear);
        return groupToResponseConverter.convert(groupRepository.saveAndFlush(group));
    }

    @NonNull
    public Group getGroupById(@NonNull Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group by id=" + id + " not found"));
    }

}
