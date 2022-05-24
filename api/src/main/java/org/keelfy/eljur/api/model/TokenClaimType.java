package org.keelfy.eljur.api.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Getter
@RequiredArgsConstructor
public enum TokenClaimType {

    USERNAME(String.class),

    ROLES(List.class),

    USER_ID(Long.class),

    GROUP_ID(Long.class),

    FIRST_NAME(String.class),

    SECOND_NAME(String.class);

    private final Class<?> objectType;

    public String getCode() {
        return toString().toLowerCase();
    }

}
