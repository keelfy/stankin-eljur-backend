package org.keelfy.eljur.api.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Getter
@RequiredArgsConstructor
public enum RestErrorCode {

    UNKNOWN(-1),

    BAD_REQUEST(0),

    FORBIDDEN(1),

    ACCESS_DENIED(2),

    BAD_CREDENTIALS(3),

    CREDENTIALS_LOCKED(4),

    OTP_CODE(5),

    NUMBER_PARSE(6),

    JWT(7),

    AUTHENTICATION(8),

    TOKEN_EXPIRED(9);

    private final int code;

}
