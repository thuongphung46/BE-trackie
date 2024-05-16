package vn.kma.hrmactvn.controller.staff.dto;


import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import vn.kma.hrmactvn.error.ExceptionMessage;

@Data
public class StaffCreateRequest {

  @NotNull(message = ExceptionMessage.USER_NAME_NULL)
  private String username;

  @NotNull(message = ExceptionMessage.PASSWORD_NULL)
  private String password;

  @NotNull(message = ExceptionMessage.LEVEL_NULL)
  private String level;

  @NotNull(message = ExceptionMessage.ACTIVE_NULL)
  private Boolean active;

  @NotNull(message = ExceptionMessage.JOB_TITLE_NULL)
  private String jobTitle;

  @Size(min = 10, max = 10, message = "Số điện thoại phải có 10 số")
  private String phoneNumber;

  @Email
  private String personalEmail;

  @Email
  private String workEmail;

  @NotNull(message = ExceptionMessage.FULL_NAME_NULL)
  private String fullName;

  @NotNull(message = ExceptionMessage.GENDER_NULL)
  @Digits(integer = 1, fraction = 0, message = "Giới tính chỉ có thể là 0 hoặc 1")
  private Integer gender;

  private String dateOfBirth;

  private Long rankId;

  private String identityBirthPlace;

  private String country;

  private String currentPlace;

  private String identityPlace; // Hộ khẩu thường trú

  private String identityCode;

  private String identityDate;

  private String placeOfIssue;

  private String favorite;

  @NotNull(message = ExceptionMessage.DEPARTMENT_ID_NULL)
  private Long departmentId;

  private Long groupId;

  @Valid
  private List<StaffAdmissionRequest> staffAdmissions; // Lịch sử vào đoàn, đảng

  @Valid
  private List<StaffWorkingHistoryRequest> staffWorkingHistories; // Lịch sử làm việc

  @Valid
  private List<StaffRankHistoryRequest> staffRankHistories; // Lịch sử quân hàm




}
