package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.SemesterSubjectResponse;
import org.keelfy.eljur.data.entity.Subject;
import org.keelfy.eljur.data.entity.embeded.SubjectPart;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@RequiredArgsConstructor
public class SubjectPartEntryToSemesterSubjectResponseConverter implements Converter<Map.Entry<Subject, SubjectPart>, SemesterSubjectResponse> {

    @NonNull
    @Override
    public SemesterSubjectResponse convert(@NonNull Map.Entry<Subject, SubjectPart> source) {
        final var target = new SemesterSubjectResponse();

        if (source.getKey() != null) {
            final var key = source.getKey();
            target.setId(key.getId()).setName(key.getName());
        }

        if (source.getValue() != null) {
            final var value = source.getValue();
            target.setFirstModule(value.getFirstModule())
                    .setSecondModule(value.getSecondModule())
                    .setCourseWork(value.getCourseWork())
                    .setExaminationType(value.getFinalExaminationType());
        }

        return target;
    }

}
