package org.keelfy.eljur.api.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Yegor Kuzmin (keelfy)
 * */
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
public class TokenAuthenticationDetails {

    private final Long userId;

}
