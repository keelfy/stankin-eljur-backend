package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.Credentials;
import org.keelfy.eljur.data.StudentSemester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Egor Kuzmin
 */
public interface StudentSemesterRepository extends JpaRepository<StudentSemester, BigInteger> {

    List<StudentSemester> findByStudent(Credentials student);

}
