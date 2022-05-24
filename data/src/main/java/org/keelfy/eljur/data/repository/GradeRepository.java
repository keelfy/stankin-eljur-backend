package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.Credentials;
import org.keelfy.eljur.data.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByRatedStudent(Credentials student);

    List<Grade> findByRatedStudentIn(List<Credentials> students);

}
