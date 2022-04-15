package org.keelfy.eljur.api.exception;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public class MailSenderException extends RuntimeException {

    private static final long serialVersionUID = 6133545914932556765L;

    public MailSenderException(String message) {
        super("Error occurred while sending mail: " + message);
    }

}
