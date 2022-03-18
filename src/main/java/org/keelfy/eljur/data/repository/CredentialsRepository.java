package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * @author Egor Kuzmin
 */
public interface CredentialsRepository extends JpaRepository<Credentials, BigInteger> {}
