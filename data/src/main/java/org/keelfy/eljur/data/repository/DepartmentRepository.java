package org.keelfy.eljur.data.repository;

import org.keelfy.eljur.data.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * @author Yegor Kuzmin (keelfy)
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
