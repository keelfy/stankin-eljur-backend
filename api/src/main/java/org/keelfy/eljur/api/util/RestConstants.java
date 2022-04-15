package org.keelfy.eljur.api.util;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public class RestConstants {

    public static final String API_ROOT_MAPPING = "/api";

    public static final String API_V1_ROOT_MAPPING = API_ROOT_MAPPING + "/v1";

    public static final String AUTH_LOGIN_URL = API_V1_ROOT_MAPPING + "/login";

    public static final String AUTH_LOGOUT_URL = API_V1_ROOT_MAPPING + "/logout";

    public static final String USERNAME_PARAMETER = "username";

    public static final String PASSWORD_PARAMETER = "password";

}
