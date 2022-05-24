package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.GradeResponse;
import org.keelfy.eljur.data.entity.Grade;
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
public class GradeToResponseConverter implements Converter<Grade, GradeResponse> {

    private final CredentialsToTeacherResponseConverter teacherResponseConverter;

    @NonNull
    @Override
    public GradeResponse convert(@NonNull Grade source) {
        return new GradeResponse()
                .setId(source.getId())
                .setGradeType(source.getGradeType())
                .setOnTime(source.getOnTime())
                .setValue(source.getValue())
                .setSemesterSubjectId(Optional.ofNullable(source.getSemesterSubject())
                        .map(SemesterSubject::getId)
                        .orElse(null))
                .setRatedBy(Optional.ofNullable(source.getRatedBy())
                        .map(teacherResponseConverter::convert)
                        .orElse(null));
    }

}
