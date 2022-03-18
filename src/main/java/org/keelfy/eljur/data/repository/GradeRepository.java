package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * @author Egor Kuzmin
 */
public interface GradeRepository extends JpaRepository<Grade, BigInteger> {}
