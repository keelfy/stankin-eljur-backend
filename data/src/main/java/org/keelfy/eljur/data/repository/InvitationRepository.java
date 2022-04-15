package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.Credentials;
import org.keelfy.eljur.data.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    List<Invitation> findByInvited(Credentials invited);

    List<Invitation> findByValidUntilBefore(ZonedDateTime now);

    Optional<Invitation> findByInvitationId(String invitationId);

}
