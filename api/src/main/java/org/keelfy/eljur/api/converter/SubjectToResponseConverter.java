package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.SubjectResponse;
import org.keelfy.eljur.data.entity.Subject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@RequiredArgsConstructor
public class SubjectToResponseConverter implements Converter<Subject, SubjectResponse> {

    @NonNull
    @Override
    public SubjectResponse convert(@NonNull Subject source) {
        return new SubjectResponse()
                .setId(source.getId())
                .setName(source.getName());
    }

}
