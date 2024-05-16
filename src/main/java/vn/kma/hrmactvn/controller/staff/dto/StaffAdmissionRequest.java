package vn.kma.hrmactvn.controller.staff.dto;



import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StaffAdmissionRequest {

  @NotNull
  private String type;

  @NotNull
  private String date;

  @NotNull
  private String place;

}
