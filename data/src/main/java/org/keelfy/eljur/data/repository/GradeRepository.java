package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface GradeRepository extends JpaRepository<Grade, Long> {}
