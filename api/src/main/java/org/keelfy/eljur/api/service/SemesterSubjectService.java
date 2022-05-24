package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.converter.SemesterSubjectToResponseConverter;
import org.keelfy.eljur.api.model.request.CreateSemesterSubjectsRequest;
import org.keelfy.eljur.api.model.response.SemesterSubjectResponse;
import org.keelfy.eljur.api.util.Authority;
import org.keelfy.eljur.data.entity.Semester;
import org.keelfy.eljur.data.entity.SemesterSubject;
import org.keelfy.eljur.data.entity.Subject;
import org.keelfy.eljur.data.repository.SemesterSubjectRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Service
@RequiredArgsConstructor
public class SemesterSubjectService {

    private final SemesterService semesterService;

    private final SubjectService subjectService;

    private final CredentialsService credentialsService;

    private final SemesterSubjectRepository semesterSubjectRepository;

    private final SemesterSubjectToResponseConverter semesterSubjectToResponseConverter;

    @NonNull
    @PreAuthorize(Authority.CHECK_ADMINISTRATION)
    public List<SemesterSubjectResponse> createSemesterSubjects(@NonNull CreateSemesterSubjectsRequest request) {
        final var semester = semesterService.getSemesterById(request.getSemesterId());
        final var list = new ArrayList<SemesterSubject>();

        for (var data : request.getSemesterSubjects()) {
            final var subject = subjectService.getSubjectById(data.getSubjectId());
            final var teacher = Optional.ofNullable(data.getTeacherId())
                    .map(credentialsService::getCredentialsById)
                    .orElse(null);

            final var semesterSubject = new SemesterSubject()
                    .setSemester(semester)
                    .setSubject(subject)
                    .setTeacher(teacher)
                    .setFinalExaminationType(data.getFinalExaminationType())
                    .setFirstModule(data.getFirstModule())
                    .setSecondModule(data.getSecondModule())
                    .setCourseWork(data.getCourseWork())
                    .setCoefficient(data.getCoefficient());
            list.add(semesterSubject);
        }

        return semesterSubjectRepository.saveAllAndFlush(list)
                .stream()
                .map(semesterSubjectToResponseConverter::convert)
                .collect(Collectors.toList());
    }

    @NonNull
    public SemesterSubject getSemesterSubjectById(@NonNull Long id) {
        return semesterSubjectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Semester subject by id=" + id + " not found"));
    }

    @NonNull
    public SemesterSubject getSemesterSubjectBySemesterAndSubject(@NonNull Semester semester, @NonNull Subject subject) {
        return semesterSubjectRepository.findBySemesterAndSubject(semester, subject)
                .orElseThrow(() -> new IllegalArgumentException("SemesterSubject by semesterId="
                        + semester.getId() + " and subjectId=" + subject.getId() + " not found"));
    }

}
