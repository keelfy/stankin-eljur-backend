package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.converter.StudentSemesterToResponseConverter;
import org.keelfy.eljur.api.exception.SemesterNotFoundException;
import org.keelfy.eljur.api.model.response.StudentSemesterResponse;
import org.keelfy.eljur.data.entity.Credentials;
import org.keelfy.eljur.data.entity.GroupSemester;
import org.keelfy.eljur.data.entity.StudentSemester;
import org.keelfy.eljur.data.model.UserCredentials;
import org.keelfy.eljur.data.repository.StudentSemesterRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Service
@RequiredArgsConstructor
public class StudentSemesterService {

    private final CredentialsService credentialsService;

    private final StudentSemesterRepository studentSemesterRepository;

    private final GroupSemesterService groupSemesterService;

    private final StudentSemesterToResponseConverter studentSemesterToResponseConverter;

    @NonNull
    @PostAuthorize("@studentSemesterService.checkStudentSemesterAccess(principal, returnObject)")
    public StudentSemesterResponse getStudentSemester() {
        final var groupSemester = groupSemesterService.getCurrentGroupSemester();
        final var studentSemester = getStudentSemesterByGroupSemester(groupSemester);
        return studentSemesterToResponseConverter.convert(studentSemester);
    }

    @NonNull
    @PostAuthorize("@studentSemesterService.checkStudentSemesterAccess(principal, returnObject)")
    public StudentSemesterResponse getStudentSemester(Long studentSemesterId) {
        final var studentSemester = getStudentSemesterById(studentSemesterId);
        return studentSemesterToResponseConverter.convert(studentSemester);
    }

    @NonNull
    @PreAuthorize("hasAnyAuthority(T(org.keelfy.eljur.api.util.Authority).STUDENT)")
    public List<Long> getStudentSemesters() {
        final var student = credentialsService.getCredentials();
        final var semesters = getStudentSemesters(student);
        return semesters.stream().map(StudentSemester::getId).collect(Collectors.toList());
    }

    public boolean checkStudentSemesterAccess(@NonNull UserCredentials credentials,
                                              @NonNull StudentSemester studentSemester) {

        if (credentialsService.isStudent(credentials)) {
            return studentSemester.getStudent().equals(credentials);
        } else {
            return true;
        }
    }

    private StudentSemester getStudentSemesterById(Long id) {
        return studentSemesterRepository.findById(id)
                .orElseThrow(() -> new SemesterNotFoundException("Student semester by id=" + id + " not found"));
    }

    private StudentSemester getStudentSemesterByGroupSemester(GroupSemester groupSemester) {
        return studentSemesterRepository.findByGroupSemester(groupSemester)
                .orElseThrow(() -> new SemesterNotFoundException("Student semester by groupSemester=" + groupSemester + " not found"));
    }

    private List<StudentSemester> getStudentSemesters(Credentials student) {
        return studentSemesterRepository.findByStudent(student);
    }

}
