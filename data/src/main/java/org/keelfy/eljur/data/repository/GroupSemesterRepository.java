package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.Group;
import org.keelfy.eljur.data.entity.GroupSemester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface GroupSemesterRepository extends JpaRepository<GroupSemester, Long> {

    List<GroupSemester> findByGroup(Group group);

}
