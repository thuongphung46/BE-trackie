package vn.kma.hrmactvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.kma.hrmactvn.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

  boolean existsByIdAndParentDeptId(Long id, Long parentDeptId);
}
