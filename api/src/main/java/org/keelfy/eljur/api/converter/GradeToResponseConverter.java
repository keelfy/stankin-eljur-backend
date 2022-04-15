package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.GradeResponse;
import org.keelfy.eljur.data.entity.Credentials;
import org.keelfy.eljur.data.entity.Grade;
import org.keelfy.eljur.data.entity.Subject;
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

    @NonNull
    @Override
    public GradeResponse convert(@NonNull Grade source) {
        return new GradeResponse()
                .setId(source.getId())
                .setGradeType(source.getGradeType())
                .setSubjectId(Optional.ofNullable(source.getSubject()).map(Subject::getId).orElse(null))
                .setOnTime(source.getOnTime())
                .setValue(source.getValue())
                .setRatedById(Optional.ofNullable(source.getRatedBy()).map(Credentials::getId).orElse(null))
                .setRatedByFullName(Optional.ofNullable(source.getRatedBy()).map(Credentials::getFullName).orElse(null));
    }

}
