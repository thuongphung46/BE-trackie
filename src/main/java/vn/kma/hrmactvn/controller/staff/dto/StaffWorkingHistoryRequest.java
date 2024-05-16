package vn.kma.hrmactvn.controller.staff.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import vn.kma.hrmactvn.error.ExceptionMessage;

@Data
public class StaffWorkingHistoryRequest {

  @NotNull(message = ExceptionMessage.DATE_NULL)
  private String date;

  @NotNull(message = ExceptionMessage.JOB_TITLE_NULL)
  private String jobTitle;

  private String bonus;

  private String discipline;


}
