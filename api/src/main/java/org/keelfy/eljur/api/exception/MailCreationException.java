package org.keelfy.eljur.api.exception;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public class MailCreationException extends RuntimeException {

    private static final long serialVersionUID = 9033186985132292978L;

    public MailCreationException(String message) {
        super("Error occurred while creating email: " + message);
    }

}
