package org.keelfy.eljur.api.util;

import lombok.experimental.UtilityClass;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@UtilityClass
public class DateTimeUtil {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String ISO_INSTANT_DATE_PATTERN = DEFAULT_DATE_PATTERN + "Z";

}
