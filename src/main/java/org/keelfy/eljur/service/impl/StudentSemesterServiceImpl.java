package org.keelfy.eljur.service.impl;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.data.Credentials;
import org.keelfy.eljur.data.StudentSemester;
import org.keelfy.eljur.data.repository.StudentSemesterRepository;
import org.keelfy.eljur.exception.SemesterNotFoundException;
import org.keelfy.eljur.model.UserCredentials;
import org.keelfy.eljur.service.CredentialsService;
import org.keelfy.eljur.service.StudentSemesterService;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Egor Kuzmin
 */
@Service
@RequiredArgsConstructor
public class StudentSemesterServiceImpl implements StudentSemesterService {

    private final CredentialsService credentialsService;

    private final SecurityContext securityContext;

    private final StudentSemesterRepository studentSemesterRepository;

    @NonNull
    @Override
    @PostAuthorize("@studentSemesterServiceImpl.checkStudentSemesterAccess(principal, returnObject)")
    public StudentSemester getStudentSemester(@NonNull BigInteger id) {
        final var studentSemester = getById(id);
        final var student = (Credentials) securityContext.getAuthentication().getPrincipal();
        return studentSemester;
    }

    @NonNull
    @Override
    @PreAuthorize("hasAnyAuthority(T(org.keelfy.eljur.util.Authority).STUDENT)")
    public List<BigInteger> getStudentSemesters() {
        final var student = (Credentials) securityContext.getAuthentication().getPrincipal();
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

    private StudentSemester getById(BigInteger id) {
        return studentSemesterRepository.findById(id)
                .orElseThrow(() -> new SemesterNotFoundException("Student semester by id=" + id + " not found"));
    }

    private List<StudentSemester> getStudentSemesters(Credentials student) {
        return studentSemesterRepository.findByStudent(student);
    }

}
