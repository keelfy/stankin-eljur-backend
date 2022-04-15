package org.keelfy.eljur.api.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.experimental.UtilityClass;

import java.util.Random;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@UtilityClass
public class PhoneNumberUtils {

    private static final Random RANDOM = new Random();

    private static final PhoneNumberUtil PHONE_NUMBER_UTIL = PhoneNumberUtil.getInstance();

    public static Phonenumber.PhoneNumber parsePhoneNumber(String phoneNumber) throws NumberParseException {
        return PHONE_NUMBER_UTIL.parse(phoneNumber, "RU");
    }

    public static String formatPhoneNumber(String phoneNumber) throws NumberParseException {
        final var parsed = parsePhoneNumber(phoneNumber);
        return formatPhoneNumber(parsed);
    }

    public static String formatPhoneNumber(Phonenumber.PhoneNumber phoneNumber) {
        return PHONE_NUMBER_UTIL.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        try {
            final var parsed = parsePhoneNumber(phoneNumber);
            return isPhoneNumberValid(parsed);
        } catch (NumberParseException ex) {
            return false;
        }
    }

    public static boolean isPhoneNumberValid(Phonenumber.PhoneNumber phoneNumber) {
        return PHONE_NUMBER_UTIL.isValidNumber(phoneNumber);
    }

}
