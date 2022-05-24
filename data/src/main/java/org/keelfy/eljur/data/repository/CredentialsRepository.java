package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.Credentials;
import org.keelfy.eljur.data.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Optional<Credentials> findByEmail(String email);

    Optional<Credentials> findByUsername(String username);

    List<Credentials> findByGroup(Group group);

    List<Credentials> findByGroupAndSecondNameIsLike(Group group, String secondName);

    List<Credentials> findByGroupIsNotNullAndSecondNameIsLike(String secondName);

}
