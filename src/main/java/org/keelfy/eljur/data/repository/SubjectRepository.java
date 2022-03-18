package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * @author Egor Kuzmin
 */
public interface SubjectRepository extends JpaRepository<Subject, BigInteger> {}
