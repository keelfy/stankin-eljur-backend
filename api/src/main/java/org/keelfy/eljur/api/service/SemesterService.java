package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.converter.SemesterToResponseConverter;
import org.keelfy.eljur.api.exception.DuplicatedEntityException;
import org.keelfy.eljur.api.model.request.CreateSemesterRequest;
import org.keelfy.eljur.api.model.response.SemesterResponse;
import org.keelfy.eljur.api.util.Authority;
import org.keelfy.eljur.data.entity.Group;
import org.keelfy.eljur.data.entity.Semester;
import org.keelfy.eljur.data.repository.SemesterRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Service
@RequiredArgsConstructor
public class SemesterService {

    private final CredentialsService credentialsService;

    private final GroupService groupService;

    private final SemesterRepository semesterRepository;

    private final SemesterToResponseConverter semesterToResponseConverter;

    @NonNull
    public SemesterResponse getSemester() {
        final var now = ZonedDateTime.now();
        final var semester = getSemestersByGroup()
                .stream()
                .filter(o -> o.getSessionDeadline().isAfter(now))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Current semester not found"));
        return semesterToResponseConverter.convert(semester);
    }

    @NonNull
    public SemesterResponse getStudentSemesterByOrdinal(@NonNull Long studentId, @NonNull Integer ordinal) {
        final var student = credentialsService.getCredentialsById(studentId);
        final var group = student.getGroup();
        final var semester = getSemesterByGroupAndOrdinal(group, ordinal);
        return semesterToResponseConverter.convert(semester);
    }

    @NonNull
    public SemesterResponse getStudentSemester(@NonNull Long studentId) {
        final var now = ZonedDateTime.now();
        final var student = credentialsService.getCredentialsById(studentId);
        final var group = student.getGroup();
        final var semester = getSemestersByGroup(group)
                .stream()
                .filter(o -> o.getSessionDeadline().isAfter(now))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Current semester of student=" + studentId + " not found"));
        return semesterToResponseConverter.convert(semester);
    }

    @NonNull
    public SemesterResponse getSemesterByOrdinal(Integer ordinal) {
        final var semester = getSemesterByGroupAndOrdinal(ordinal);
        return semesterToResponseConverter.convert(semester);
    }

    @NonNull
    public List<SemesterResponse> getSemesters() {
        return getSemestersByGroup()
                .stream()
                .map(semesterToResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @NonNull
    public SemesterResponse getCurrentGroupSemester(@NonNull Long groupId) {
        final var now = ZonedDateTime.now();
        final var group = groupService.getGroupById(groupId);
        final var semester = getSemestersByGroup(group)
                .stream()
                .filter(o -> o.getSessionDeadline().isAfter(now))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Current group semester not found"));
        return semesterToResponseConverter.convert(semester);
    }

    @NonNull
    public List<SemesterResponse> getGroupSemesters(@NonNull Long groupId) {
        final var group = groupService.getGroupById(groupId);
        return getSemestersByGroup(group)
                .stream()
                .map(semesterToResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @NonNull
    public SemesterResponse getGroupSemesterByOrdinal(@NonNull Long groupId, @NonNull Integer ordinal) {
        final var group = groupService.getGroupById(groupId);
        final var semester = getSemesterByGroupAndOrdinal(group, ordinal);
        return semesterToResponseConverter.convert(semester);
    }

    @NonNull
    public SemesterResponse getSemesterByIdAndOrdinal(@NonNull Long semesterId, @NonNull Integer ordinal) {
        return semesterRepository.findByIdAndOrdinal(semesterId, ordinal)
                .map(semesterToResponseConverter::convert)
                .orElseThrow(() -> new IllegalArgumentException("Semester by {id=" + semesterId + "ordinal=" + ordinal + "} not found"));
    }

    @NonNull
    @PreAuthorize(Authority.CHECK_ADMINISTRATION)
    public SemesterResponse createSemester(@NonNull CreateSemesterRequest request) {
        final var group = groupService.getGroupById(request.getGroupId());
        final var semesters = group.getSemesters();
        if (semesters.stream().anyMatch(semester -> semester.getOrdinal().equals(request.getOrdinal()))) {
            throw new DuplicatedEntityException("Semester by ordinal=" + request.getOrdinal() + " already exists");
        }

        final var semester = new Semester()
                .setGroup(group)
                .setOrdinal(request.getOrdinal())
                .setFirstModuleDeadline(request.getFirstModuleDeadline().atZone(ZoneId.systemDefault()))
                .setSecondModuleDeadline(request.getSecondModuleDeadline().atZone(ZoneId.systemDefault()))
                .setSessionDeadline(request.getSessionDeadline().atZone(ZoneId.systemDefault()));
        return semesterToResponseConverter.convert(semesterRepository.saveAndFlush(semester));
    }

    @NonNull
    public Semester getSemesterById(@NonNull Long id) {
        return semesterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group semester by id=" + id + " not found"));
    }

    @NonNull
    public List<Semester> getSemestersByGroup() {
        final var student = credentialsService.getCredentials();
        final var group = student.getGroup();
        return getSemestersByGroup(group);
    }

    @NonNull
    public List<Semester> getSemestersByGroup(@Nullable Group group) {
        if (group == null) {
            throw new IllegalArgumentException("No group");
        }

        return semesterRepository.findByGroup(group)
                .stream()
                .sorted(Comparator.comparing(Semester::getOrdinal))
                .collect(Collectors.toList());
    }

    @NonNull
    public Semester getSemesterByGroupAndOrdinal(@NonNull Integer ordinal) {
        final var student = credentialsService.getCredentials();
        final var group = student.getGroup();
        return getSemesterByGroupAndOrdinal(group, ordinal);
    }

    @NonNull
    public Semester getSemesterByGroupAndOrdinal(@Nullable Group group, @NonNull Integer ordinal) {
        if (group == null) {
            throw new IllegalArgumentException("No group");
        }

        return semesterRepository.findByGroupAndOrdinal(group, ordinal)
                .orElseThrow(() -> new IllegalArgumentException("Semester by ordinal=" + ordinal + " not found"));
    }

}
