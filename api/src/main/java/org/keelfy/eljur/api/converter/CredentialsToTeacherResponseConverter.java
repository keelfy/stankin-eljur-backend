package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.TeacherResponse;
import org.keelfy.eljur.data.entity.Credentials;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@RequiredArgsConstructor
public class CredentialsToTeacherResponseConverter implements Converter<Credentials, TeacherResponse> {

    @NonNull
    @Override
    public TeacherResponse convert(@NonNull Credentials source) {
        return new TeacherResponse()
                .setId(source.getId())
                .setEmail(source.getEmail())
                .setFirstName(source.getFirstName())
                .setSecondName(source.getSecondName())
                .setMiddleName(source.getMiddleName());
    }

}
