package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * @author Egor Kuzmin
 */
public interface GroupRepository extends JpaRepository<Group, BigInteger> {}
