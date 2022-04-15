package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface SubjectRepository extends JpaRepository<Subject, Long> {}
