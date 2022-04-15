package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.api.configuration.property.MailMessagePropertiesProvider;
import org.keelfy.eljur.api.configuration.property.MailProperties;
import org.keelfy.eljur.api.exception.MailCreationException;
import org.keelfy.eljur.api.exception.MailSenderException;
import org.keelfy.eljur.api.model.MailDto;
import org.keelfy.eljur.data.entity.MailJournal;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.MissingFormatArgumentException;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender sender;

    private final MailJournalService mailJournalService;

    private final MailAttachmentService attachmentService;

    private final MailProperties mailProperties;

    private final TemplateEngine templateEngine;

    public MailJournal sendWithRetry(String receiver, MailMessagePropertiesProvider provider) {
        return this.sendWithRetry(receiver, provider, Map.of());
    }

    public MailJournal sendWithRetry(String receiver, MailMessagePropertiesProvider provider, Map<String, Object> variables) {
        final var ctx = createThymeleafContext(variables);
        final var message = createMessage(provider, ctx);
        return sendWithRetry(receiver, provider, message);
    }

    public MailJournal sendWithRetry(String receiver, MailMessagePropertiesProvider provider, String message) {
        final var mailDto = new MailDto(mailProperties, receiver, provider, message);
        try {
            send(mailDto);
            return mailJournalService.saveSuccess(mailDto);
        } catch (MailSenderException e) {
            return mailJournalService.saveFail(mailDto, e.getMessage());
        }
    }

    @SneakyThrows
    void send(MailDto dto) {
        final var mimeMessage = sender.createMimeMessage();
        final var helper = new MimeMessageHelper(mimeMessage, true);
        try {
            helper.setTo(dto.getTo());
            helper.setFrom(mailProperties.getSender());
            helper.setSubject(dto.getSubject());
            helper.setText(dto.getMessage(), true);
            addAttachments(dto.getAttachmentDirectory(), helper);
        } catch (MessagingException e) {
            log.error("Error occurred while creating email", e);
            throw new MailCreationException(e.getMessage());
        }
        send(mimeMessage);
    }

    private void addAttachments(String attachmentDirectory, MimeMessageHelper helper) {
        final var directory = this.createAttachmentPath(attachmentDirectory);
        final var directoryFile = new File(directory);
        attachmentService.listAttachmentsInDirectory(directoryFile)
                .forEach(file -> addAsAttachment(helper, file));
    }

    private void addAsAttachment(MimeMessageHelper helper, File attachmentFile) {
        final var fileName = attachmentFile.getName();
        try {
            helper.addAttachment(fileName, attachmentFile);
        } catch (javax.mail.MessagingException ex) {
            log.error("Error occurred while attaching file to mail. File skipped", ex);
        }
    }

    private Context createThymeleafContext(Map<String, Object> variables) {
        final var ctx = new Context();
        ctx.setLocale(new Locale("RU"));
        ctx.setVariables(variables);
        return ctx;
    }

    private String createAttachmentPath(String attachmentDirectory) {
        return mailProperties.getAttachmentDirectory() + File.pathSeparator + attachmentDirectory;
    }

    private String createMessage(MailMessagePropertiesProvider provider, Context context) {
        return this.templateEngine.process(provider.getMessageTemplateName() + ".html", context);
    }

    private void send(MimeMessage mimeMessage) {
        try {
            sender.send(mimeMessage);
        } catch (RuntimeException e) {
            log.error("Error occurred while sending email", e);
            throw new MailSenderException(e.getMessage());
        }
    }

    private String getFormattedText(MailDto mailDto, Object... args) {
        final var message = mailDto.getMessage();
        try {
            return String.format(message, args);
        } catch (MissingFormatArgumentException e) {
            log.warn("Cannot format message to {} on subject '{}' with arguments",
                    mailDto.getTo(), mailDto.getSubject(), e);
            return message;
        }
    }

}
