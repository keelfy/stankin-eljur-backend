package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.Group;
import org.keelfy.eljur.data.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface SemesterRepository extends JpaRepository<Semester, Long> {

    List<Semester> findByGroup(Group group);

    Optional<Semester> findByGroupAndOrdinal(Group group, Integer ordinal);

    Optional<Semester> findByIdAndOrdinal(Long id, Integer ordinal);

}
