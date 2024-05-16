package vn.kma.hrmactvn.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.kma.hrmactvn.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

  Optional<Staff> findByUsernameAndActive(String username, boolean active);

  boolean existsByUsername(String username);

}
