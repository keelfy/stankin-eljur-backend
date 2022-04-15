package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.MailJournal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface MailJournalRepository extends JpaRepository<MailJournal, Long> {

}
