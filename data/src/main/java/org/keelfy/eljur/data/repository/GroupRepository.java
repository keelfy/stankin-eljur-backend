package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByActive(Boolean active);

}
