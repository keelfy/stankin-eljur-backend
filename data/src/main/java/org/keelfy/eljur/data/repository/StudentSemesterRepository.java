package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.Credentials;
import org.keelfy.eljur.data.entity.StudentSemester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Egor Kuzmin
 */
public interface StudentSemesterRepository extends JpaRepository<StudentSemester, BigInteger> {

    List<StudentSemester> findByStudent(Credentials student);

}
