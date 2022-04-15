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

    USER_ID(Long.class);

    private final Class<?> objectType;

}
