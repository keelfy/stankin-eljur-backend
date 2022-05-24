package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.response.StudentResponse;
import org.keelfy.eljur.data.entity.Credentials;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@RequiredArgsConstructor
public class CredentialsToStudentResponseConverter implements Converter<Credentials, StudentResponse> {

    private final GroupToShortResponseConverter groupToResponseConverter;

    @NonNull
    @Override
    public StudentResponse convert(@NonNull Credentials source) {
        return new StudentResponse()
                .setId(source.getId())
                .setEmail(source.getEmail())
                .setFirstName(source.getFirstName())
                .setSecondName(source.getSecondName())
                .setMiddleName(source.getMiddleName())
                .setGroup(Optional.ofNullable(source.getGroup())
                        .map(groupToResponseConverter::convert)
                        .orElse(null));
    }

}
