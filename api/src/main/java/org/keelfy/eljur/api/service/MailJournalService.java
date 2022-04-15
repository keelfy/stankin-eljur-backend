package org.keelfy.eljur.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.api.model.MailDto;
import org.keelfy.eljur.data.entity.MailJournal;
import org.keelfy.eljur.data.model.MailJournalStatus;
import org.keelfy.eljur.data.repository.MailJournalRepository;
import org.springframework.stereotype.Service;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MailJournalService {

    private final MailJournalRepository mailJournalRepository;

    public MailJournal saveSuccess(MailDto mailDto) {
        final var mailJournal = getMailJournal(mailDto, MailJournalStatus.SUCCESS);
        return mailJournalRepository.saveAndFlush(mailJournal);
    }

    public MailJournal saveFail(MailDto mailDto, String error) {
        final var mailJournal = getMailJournal(mailDto, MailJournalStatus.NEW);
        mailJournal.setError(error);
        return mailJournalRepository.saveAndFlush(mailJournal);
    }

    private MailJournal getMailJournal(MailDto mailDto, MailJournalStatus status) {
        return new MailJournal()
                .setSender(mailDto.getFrom())
                .setReceiver(mailDto.getTo())
                .setSubject(mailDto.getSubject())
                .setMessage(mailDto.getMessage())
                .setAttachment(mailDto.getAttachmentDirectory())
                .setStatus(status);
    }

    public void save(MailJournal journal) {
        mailJournalRepository.saveAndFlush(journal);
    }

}
