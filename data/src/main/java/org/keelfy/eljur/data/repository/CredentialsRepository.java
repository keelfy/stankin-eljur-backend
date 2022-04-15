package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Optional<Credentials> findByEmail(String email);

    Optional<Credentials> findByUsername(String username);

}
