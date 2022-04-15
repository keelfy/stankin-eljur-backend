package org.keelfy.eljur.api.configuration.property;

/**
 * @author Yegor Kuzmin (keelfy)
 * */
public interface MailMessagePropertiesProvider {

    /**
     * Тема письма.
     * */
    String getSubject();

    /**
     * Название файла шаблона.
     * */
    String getMessageTemplateName();

    /**
     * Наименование папки с прикрепляемыми сообщениями.
     * */
    default String getAttachmentDirectory() {
        return getMessageTemplateName();
    }

}
