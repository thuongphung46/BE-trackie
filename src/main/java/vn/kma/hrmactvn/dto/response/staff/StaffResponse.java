package vn.kma.hrmactvn.dto.response.staff;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.kma.hrmactvn.entity.Staff;
import vn.kma.hrmactvn.entity.StaffInfo;
import vn.kma.hrmactvn.utils.CommonUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffResponse {
    private String username;
    private String level;
    private Boolean active;
    private String jobTitle;
    private String phoneNumber;
    private String personalEmail;
    private String workEmail;
    private String gender;
    private String dateOfBirth;
    private String rankName;
    private String identityBirthPlace;
    private String country;
    private String currentPlace;
    private String identityPlace;
    private String identityCode;
    private String identityDate;
    private String placeOfIssue;
    private String favorite;
    private String department;
    private String group;
    private String departmentId;
    private String groupId;
    private List<StaffAdmissionResponse> staffAdmissions;
    private List<StaffRankHistoryResponse> staffRankHistories;
    private List<StaffWorkingHistoryResponse> staffWorkingHistories;


    public static StaffResponse from(Staff staff, StaffInfo staffInfo){
        String gender = staffInfo.getGender() == 1 ? "Nam" : "Nữ";
        return StaffResponse.builder()
            .username(staff.getUsername())
            .level(staff.getLevel())
            .active(staff.getActive())
            .jobTitle(staff.getJobTitle())
            .rankName(staffInfo.getRank())
            .phoneNumber(staff.getPhoneNumber())
            .personalEmail(staff.getPersonalEmail())
            .workEmail(staff.getWorkEmail())
            .gender(gender)
            .dateOfBirth(CommonUtils.convertDateFromDomainToString(staffInfo.getDateOfBirth()))
            .identityBirthPlace(staffInfo.getIdentityBirthPlace())
            .country(staffInfo.getCountry())
            .currentPlace(staffInfo.getCurrentPlace())
            .identityPlace(staffInfo.getIdentityPlace())
            .identityCode(staffInfo.getIdentityCode())
            .identityDate(CommonUtils.convertDateFromDomainToString(staffInfo.getIdentityDate()))
            .placeOfIssue(staffInfo.getPlaceOfIssue())
            .favorite(staffInfo.getFavorite())
            .build();

    }
}
