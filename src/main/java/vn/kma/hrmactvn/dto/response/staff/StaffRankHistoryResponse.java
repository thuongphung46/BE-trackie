package vn.kma.hrmactvn.dto.response.staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.kma.hrmactvn.dto.query_response.StaffRankHistoryQueryResponse;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffRankHistoryResponse {

  private Long rankId;
  private String rankName;
  private String date;

  public static StaffRankHistoryResponse from(StaffRankHistoryQueryResponse response){
    return StaffRankHistoryResponse.builder()
        .rankId(response.getRankId())
        .rankName(response.getRankName())
        .date(response.getDate())
        .build();
  }
}
