package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.Credentials;
import org.keelfy.eljur.data.entity.GroupSemester;
import org.keelfy.eljur.data.entity.StudentSemester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface StudentSemesterRepository extends JpaRepository<StudentSemester, Long> {

    List<StudentSemester> findByStudent(Credentials student);

    Optional<StudentSemester> findByGroupSemester(GroupSemester groupSemester);

}
