package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.converter.GradeToResponseConverter;
import org.keelfy.eljur.api.model.request.CreateGradeRequest;
import org.keelfy.eljur.api.model.response.GradeResponse;
import org.keelfy.eljur.api.util.Authority;
import org.keelfy.eljur.data.entity.Grade;
import org.keelfy.eljur.data.repository.GradeRepository;
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
public class GradeService {

    private final GradeRepository gradeRepository;

    private final CredentialsService credentialsService;

    private final GroupService groupService;

    private final SemesterSubjectService semesterSubjectService;

    private final GradeToResponseConverter gradeToResponseConverter;

    @NonNull
    @PreAuthorize(Authority.CHECK_STUDENT)
    public List<GradeResponse> getGrades() {
        final var student = credentialsService.getCredentials();
        final var grades = gradeRepository.findByRatedStudent(student);
        return grades.stream().map(gradeToResponseConverter::convert).collect(Collectors.toList());
    }

    @PreAuthorize(Authority.CHECK_ADMINISTRATION)
    public List<GradeResponse> getGroupStudentGrades(Long groupId) {
        final var group = groupService.getGroupById(groupId);
        final var students = group.getStudents();
        final var grades = gradeRepository.findByRatedStudentIn(students);
        return grades.stream().map(gradeToResponseConverter::convert).collect(Collectors.toList());
    }

    @PreAuthorize(Authority.CHECK_ADMINISTRATION)
    public GradeResponse rateStudent(CreateGradeRequest request) {
        final var student = credentialsService.getCredentialsById(request.getRatedStudentId());
        if (student.getGroup() == null) {
            throw new IllegalArgumentException("Rated student must be in the group");
        }

        final var semesterSubject = semesterSubjectService.getSemesterSubjectById(request.getSemesterSubjectId());
        final var ratedBy = Optional.ofNullable(request.getRatedById())
                .map(credentialsService::getCredentialsById)
                .orElse(null);
        final var grade = new Grade()
                .setGradeType(request.getGradeType())
                .setValue(request.getValue())
                .setRatedStudent(student)
                .setOnTime(request.getOnTime())
                .setExtendedDeadline(request.getDeadlineExtended())
                .setRatedBy(ratedBy)
                .setSemesterSubject(semesterSubject);
        return gradeToResponseConverter.convert(gradeRepository.saveAndFlush(grade));
    }

    @NonNull
    @PreAuthorize(Authority.CHECK_ADMINISTRATION)
    public List<GradeResponse> gradesByStudentId(@NonNull Long studentId) {
        final var student = credentialsService.getCredentialsById(studentId);
        final var grades = gradeRepository.findByRatedStudent(student);
        return grades.stream().map(gradeToResponseConverter::convert).collect(Collectors.toList());
    }

}
