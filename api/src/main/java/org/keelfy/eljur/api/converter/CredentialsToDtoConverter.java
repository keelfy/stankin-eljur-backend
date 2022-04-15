package org.keelfy.eljur.api.converter;

import lombok.RequiredArgsConstructor;
import org.keelfy.eljur.api.model.CredentialsDto;
import org.keelfy.eljur.data.entity.Credentials;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Component
@RequiredArgsConstructor
public final class CredentialsToDtoConverter implements Converter<Credentials, CredentialsDto> {

    @Override
    @NonNull
    public CredentialsDto convert(@NonNull Credentials source) {
        return new CredentialsDto()
                .setId(source.getId())
                .setEmail(source.getEmail())
                .setUsername(source.getUsername())
                .setPhoneNumber(source.getPhoneNumber())
                .setRoles(source.getLiteralAuthorities())
                .setLocked(source.getLocked())
                .setEnabled(source.getEnabled());
    }

}
