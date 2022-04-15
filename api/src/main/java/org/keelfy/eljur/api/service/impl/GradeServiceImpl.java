package org.keelfy.eljur.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.exception.CannotRateException;
import org.keelfy.eljur.api.model.request.RateStudentRequest;
import org.keelfy.eljur.api.service.CredentialsService;
import org.keelfy.eljur.api.service.GradeService;
import org.keelfy.eljur.api.service.StudentSemesterService;
import org.keelfy.eljur.data.entity.Grade;
import org.keelfy.eljur.data.repository.GradeRepository;
import org.springframework.stereotype.Service;

/**
 * @author Yegor Kuzmin (keelfy)
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
        final var ratedBy = credentialsService.getCredentialsById(request.getRatedBy());

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
