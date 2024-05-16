package vn.kma.hrmactvn.controller.staff.dto;


import javax.validation.constraints.NotNull;
import lombok.Data;
import vn.kma.hrmactvn.error.ExceptionMessage;

@Data
public class StaffRankHistoryRequest {

  @NotNull
  private Long rankId;

  @NotNull(message = ExceptionMessage.DATE_NULL)
  private String date;

}
