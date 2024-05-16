package vn.kma.hrmactvn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.kma.hrmactvn.dto.query_response.StaffRankHistoryQueryResponse;
import vn.kma.hrmactvn.entity.StaffRankHistory;

public interface StaffRankHistoryRepository extends JpaRepository<StaffRankHistory, Long> {


  @Query(
      nativeQuery = true,
      value = "SELECT hsrh.id as rankId,\n"
          + "         hsrh.date as date,\n"
          + "         hrf.rank_name as rankName\n"
          + "from hrm_staff_rank_history hsrh\n"
          + "         left join hrm_rank_config hrf on hsrh.rank_id = hrf.id\n"
          + "where hsrh.staff_id = :staffId\n"
          + "order by hsrh.date desc\n"
  )
  List<StaffRankHistoryQueryResponse> getStaffRankHistory(@Param("staffId") Long staffId);
}
