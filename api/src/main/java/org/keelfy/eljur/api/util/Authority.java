package org.keelfy.eljur.api.util;

import lombok.experimental.UtilityClass;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@UtilityClass
public class Authority {

    public static final String STUDENT = "student";

    public static final String TEACHER = "teacher";

    public static final String ADMIN = "admin";

    public static final String SYSTEM = "system";

    public static final String CHECK_ADMINISTRATION = "hasAnyAuthority(T(org.keelfy.eljur.api.util.Authority).ADMIN, " +
            "T(org.keelfy.eljur.api.util.Authority).SYSTEM)";

    public static final String CHECK_STUDENT = "hasAuthority(T(org.keelfy.eljur.api.util.Authority).STUDENT";

}
