package vn.kma.hrmactvn.service;


import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import vn.kma.hrmactvn.authentication.UserDetailsImpl;
import vn.kma.hrmactvn.constant.ApplicationConstant.LEVEL;
import vn.kma.hrmactvn.controller.staff.dto.StaffCreateRequest;
import vn.kma.hrmactvn.controller.staff.dto.StaffRankHistoryRequest;
import vn.kma.hrmactvn.dto.query_response.StaffRankHistoryQueryResponse;
import vn.kma.hrmactvn.dto.response.staff.StaffRankHistoryResponse;
import vn.kma.hrmactvn.dto.response.staff.StaffResponse;
import vn.kma.hrmactvn.entity.Staff;
import vn.kma.hrmactvn.entity.StaffInfo;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.error.ExceptionMessage;
import vn.kma.hrmactvn.repository.StaffInfoRepository;
import vn.kma.hrmactvn.repository.StaffRepository;
import vn.kma.hrmactvn.utils.ApplicationUtils;
import vn.kma.hrmactvn.utils.CommonUtils;

@RequiredArgsConstructor
@Service
public class StaffService {

  private final StaffRepository staffRepository;
  private final PasswordEncoder passwordEncoder;
  private final StaffInfoRepository staffInfoRepository;


  @Transactional(rollbackFor = Exception.class)
  public Staff create(StaffCreateRequest request) throws ActvnException {
    UserDetailsImpl userDetails = ApplicationUtils.currentUser();
    String authName = userDetails.getUsername();
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    boolean exists = staffRepository.existsByUsername(request.getUsername());

    if(exists){
      throw new ActvnException(HttpStatus.BAD_REQUEST.value(), ExceptionMessage.USERNAME_EXISTS);
    }
    commonCheck(request);

    Staff staff = Staff.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .level(request.getLevel())
        .createdBy(authName)
        .modifiedBy(authName)
        .createdDate(timestamp)
        .modifiedDate(timestamp)
        .jobTitle(request.getJobTitle())
        .active(request.getActive())
        .personalEmail(request.getPersonalEmail())
        .workEmail(request.getWorkEmail())
        .phoneNumber(request.getPhoneNumber())
        .build();
    staffRepository.save(staff);

    Long staffId = staff.getId();


    StaffInfo staffInfo = StaffInfo.builder()
        .staffId(staffId)
        .fullName(request.getFullName())
        .gender(request.getGender())
        .dateOfBirth(CommonUtils.convertStringToTimeStamp(request.getDateOfBirth()))
        .identityBirthPlace(request.getIdentityBirthPlace())
        .country(request.getCountry())
        .currentPlace(request.getCurrentPlace())
        .identityPlace(request.getIdentityPlace())
        .identityCode(request.getIdentityCode())
        .identityDate(CommonUtils.convertStringToTimeStamp(request.getIdentityDate()))
        .placeOfIssue(request.getPlaceOfIssue())
        .favorite(request.getFavorite())
        .createdBy(authName)
        .modifiedBy(authName)
        .createdDate(timestamp)
        .modifiedDate(timestamp)
        .build();
    staffInfoRepository.save(staffInfo);

    return staff;
  }


  public StaffResponse getMe() throws ActvnException {
    UserDetailsImpl userDetails = ApplicationUtils.currentUser();
    String username = userDetails.getUsername();
    Optional<Staff> staffOptional = staffRepository.findByUsernameAndActive(username, true);
    if (staffOptional.isEmpty()) {
      throw new ActvnException(HttpStatus.NOT_FOUND.value(), ExceptionMessage.STAFF_NOT_FOUND);
    }
    Staff staff = staffOptional.get();
    Optional<StaffInfo> staffInfoOptional = staffInfoRepository.findByStaffId(staff.getId());
    if (staffInfoOptional.isEmpty()) {
      throw new ActvnException(HttpStatus.NOT_FOUND.value(), ExceptionMessage.STAFF_INFO_NOT_FOUND);
    }
    StaffInfo staffInfo = staffInfoOptional.get();
    StaffResponse staffResponse = StaffResponse.from(staff, staffInfo);
    // TODO: add staffAdmissions and staffWorkingHistories after, group, dept. Now i'm very tired
     return  staffResponse;
  }


  private void commonCheck(StaffCreateRequest request) throws ActvnException {
    if (StringUtils.hasText(request.getIdentityCode())){
      if (request.getIdentityCode().length() != 9 && request.getIdentityCode().length() != 12) {
        throw new ActvnException(HttpStatus.BAD_REQUEST.value(), "Số CMND không hợp lệ");
      }
      boolean existsIdentityCode = staffInfoRepository.existsByIdentityCode(request.getIdentityCode());
      if (existsIdentityCode) {
        throw new ActvnException(HttpStatus.BAD_REQUEST.value(), "Số CMND đã tồn tại");
      }
    }
  }
}
