package vn.kma.hrmactvn.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.kma.hrmactvn.entity.StaffInfo;

public interface StaffInfoRepository  extends JpaRepository<StaffInfo, Long> {


  boolean existsByIdentityCode(String identityCode);


  Optional<StaffInfo> findByStaffId(Long id);
}
