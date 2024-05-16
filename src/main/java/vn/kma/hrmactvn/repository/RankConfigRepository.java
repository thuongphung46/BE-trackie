package vn.kma.hrmactvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.kma.hrmactvn.entity.RankConfig;

public interface RankConfigRepository extends JpaRepository<RankConfig, Long> {


  @Query(
      nativeQuery = true,
      value = "SELECT rank_name from hrm_rank_config where id =:rankId ;"
  )
  String getRankNameById(@Param("rankId") Long rankId);


}
