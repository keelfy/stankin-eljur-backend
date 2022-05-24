package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.SemesterSubjectResponse;
import org.keelfy.eljur.data.entity.SemesterSubject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@RequiredArgsConstructor
public class SemesterSubjectToResponseConverter implements Converter<SemesterSubject, SemesterSubjectResponse> {

    private final SubjectToResponseConverter subjectToResponseConverter;

    private final CredentialsToTeacherResponseConverter teacherResponseConverter;

    @NonNull
    @Override
    public SemesterSubjectResponse convert(@NonNull SemesterSubject source) {
        return new SemesterSubjectResponse()
                .setId(source.getId())
                .setExaminationType(source.getFinalExaminationType())
                .setCourseWork(source.getCourseWork())
                .setSecondModule(source.getSecondModule())
                .setFirstModule(source.getFirstModule())
                .setCoefficient(source.getCoefficient())
                .setSubject(Optional.ofNullable(source.getSubject())
                        .map(subjectToResponseConverter::convert)
                        .orElse(null))
                .setTeacher(Optional.ofNullable(source.getTeacher())
                        .map(teacherResponseConverter::convert)
                        .orElse(null));
    }

}
