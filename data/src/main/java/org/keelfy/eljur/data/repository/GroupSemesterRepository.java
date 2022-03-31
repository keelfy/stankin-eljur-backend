package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.GroupSemester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * @author Egor Kuzmin
 */
public interface GroupSemesterRepository extends JpaRepository<GroupSemester, BigInteger> {}
