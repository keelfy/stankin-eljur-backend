package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.converter.CredentialsToStudentResponseConverter;
import org.keelfy.eljur.api.model.request.EditStudentRequest;
import org.keelfy.eljur.api.model.response.StudentResponse;
import org.keelfy.eljur.api.util.Authority;
import org.keelfy.eljur.data.entity.Credentials;
import org.keelfy.eljur.data.repository.CredentialsRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Service
@RequiredArgsConstructor
public class StudentService {

    private final GroupService groupService;

    private final CredentialsService credentialsService;

    private final CredentialsRepository credentialsRepository;

    private final CredentialsToStudentResponseConverter credentialsToStudentResponseConverter;

    @NonNull
    public StudentResponse studentById(@NonNull Long id) {
        return credentialsToStudentResponseConverter.convert(credentialsService.getCredentialsById(id));
    }

    @NonNull
    @PreAuthorize(Authority.CHECK_ADMINISTRATION)
    public StudentResponse editStudent(@NonNull Long studentId, @NonNull EditStudentRequest request) {
        final var student = credentialsService.getCredentialsById(studentId);
        Optional.ofNullable(request.getFirstName()).ifPresent(student::setFirstName);
        Optional.ofNullable(request.getSecondName()).ifPresent(student::setSecondName);
        Optional.ofNullable(request.getMiddleName()).ifPresent(student::setMiddleName);
        return credentialsToStudentResponseConverter.convert(credentialsRepository.saveAndFlush(student));
    }

    @NonNull
    public List<StudentResponse> getStudentsOfGroup(@NonNull Long groupId) {
        final var group = groupService.getGroupById(groupId);
        return credentialsRepository.findByGroup(group)
                .stream()
                .sorted(Comparator.comparing(Credentials::getSecondName))
                .map(credentialsToStudentResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @NonNull
    public List<StudentResponse> searchStudentsBySecondName(@NonNull String secondName) {
        return credentialsRepository.findByGroupIsNotNullAndSecondNameIsLike(secondName)
                .stream()
                .sorted(Comparator.comparing(Credentials::getSecondName))
                .map(credentialsToStudentResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @NonNull
    public List<StudentResponse> searchStudentsByGroupAndSecondName(@NonNull Long groupId, @NonNull String secondName) {
        final var group = groupService.getGroupById(groupId);
        return credentialsRepository.findByGroupAndSecondNameIsLike(group, secondName)
                .stream()
                .sorted(Comparator.comparing(Credentials::getSecondName))
                .map(credentialsToStudentResponseConverter::convert)
                .collect(Collectors.toList());
    }

}
