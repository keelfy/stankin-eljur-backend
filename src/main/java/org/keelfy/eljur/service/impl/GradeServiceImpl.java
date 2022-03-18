package org.keelfy.eljur.service.impl;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.data.Grade;
import org.keelfy.eljur.data.repository.GradeRepository;
import org.keelfy.eljur.exception.CannotRateException;
import org.keelfy.eljur.model.request.RateStudentRequest;
import org.keelfy.eljur.service.CredentialsService;
import org.keelfy.eljur.service.GradeService;
import org.keelfy.eljur.service.StudentSemesterService;
import org.springframework.stereotype.Service;

/**
 * @author Egor Kuzmin
 */
@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final CredentialsService credentialsService;

    private final StudentSemesterService studentSemesterService;

    private final GradeRepository gradeRepository;

    @Override
    public Grade rateStudent(RateStudentRequest request) {
        final var semester = studentSemesterService.getStudentSemester(request.getStudentSemesterId());
        final var ratedBy = credentialsService.getById(request.getRatedBy());
        if (credentialsService.isStudent(ratedBy)) {
            throw new CannotRateException("Credentials by id=" + request.getRatedBy() + " can't rate students");
        }
        final var grade = new Grade()
                .setGradeType(request.getGradeType())
                .setStudentSemester(semester)
                .setValue(request.getValue())
                .setOnTime(request.getOnTime()) // TODO: Calculate time after onTime field is null
                .setRatedBy(ratedBy);
        return gradeRepository.saveAndFlush(grade);
    }

}
