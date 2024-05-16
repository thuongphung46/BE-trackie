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
import vn.kma.hrmactvn.entity.StaffAdmission;
import vn.kma.hrmactvn.entity.StaffDepartment;
import vn.kma.hrmactvn.entity.StaffInfo;
import vn.kma.hrmactvn.entity.StaffRankHistory;
import vn.kma.hrmactvn.entity.StaffWorkingHistory;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.error.ExceptionMessage;
import vn.kma.hrmactvn.repository.DepartmentRepository;
import vn.kma.hrmactvn.repository.RankConfigRepository;
import vn.kma.hrmactvn.repository.StaffAdmissionRepository;
import vn.kma.hrmactvn.repository.StaffDepartmentRepository;
import vn.kma.hrmactvn.repository.StaffInfoRepository;
import vn.kma.hrmactvn.repository.StaffRankHistoryRepository;
import vn.kma.hrmactvn.repository.StaffRepository;
import vn.kma.hrmactvn.repository.StaffWorkingHistoryRepository;
import vn.kma.hrmactvn.utils.ApplicationUtils;
import vn.kma.hrmactvn.utils.CommonUtils;

@RequiredArgsConstructor
@Service
public class StaffService {

  private final StaffRepository staffRepository;
  private final PasswordEncoder passwordEncoder;
  private final DepartmentRepository departmentRepository;
  private final StaffDepartmentRepository staffDepartmentRepository;
  private final RankConfigRepository rankConfigRepository;
  private final StaffInfoRepository staffInfoRepository;
  private final StaffAdmissionRepository staffAdmissionRepository;
  private final StaffWorkingHistoryRepository staffWorkingHistoryRepository;
  private final StaffRankHistoryRepository staffRankHistoryRepository;


  @Transactional(rollbackFor = Exception.class)
  public Staff create(StaffCreateRequest request) throws ActvnException {
    UserDetailsImpl userDetails = ApplicationUtils.currentUser();
    String authName = userDetails.getUsername();
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    boolean exists = staffRepository.existsByUsername(request.getUsername());

    if(exists){
      throw new ActvnException(HttpStatus.BAD_REQUEST.value(), ExceptionMessage.USERNAME_EXISTS);
    }
    checkDepartment(request);
    checkRank(request);
    commonCheck(request);
    checkStaffAdmissions(request);
    checkStaffRankHistories(request);

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

    StaffDepartment staffDepartment = StaffDepartment.builder()
        .staffId(staffId)
        .departmentId(request.getDepartmentId())
        .groupId(request.getGroupId())
        .createdBy(authName)
        .modifiedBy(authName)
        .createdDate(timestamp)
        .modifiedDate(timestamp)
        .build();
    staffDepartmentRepository.save(staffDepartment);

    String rankName = rankConfigRepository.getRankNameById(request.getRankId());

    StaffInfo staffInfo = StaffInfo.builder()
        .staffId(staffId)
        .fullName(request.getFullName())
        .gender(request.getGender())
        .dateOfBirth(CommonUtils.convertStringToTimeStamp(request.getDateOfBirth()))
        .rank(rankName)
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

    if (request.getStaffAdmissions()!=null && !request.getStaffAdmissions().isEmpty()) {
      List<StaffAdmission> admissionList = request.getStaffAdmissions().stream()
          .map(admission -> StaffAdmission.builder()
              .staffId(staffId)
              .type(admission.getType())
              .date(CommonUtils.convertStringToTimeStamp(admission.getDate()))
              .place(admission.getPlace())
              .createdBy(authName)
              .modifiedBy(authName)
              .createdDate(timestamp)
              .modifiedDate(timestamp)
              .build()).collect(Collectors.toList());
      staffAdmissionRepository.saveAll(admissionList);
    }
    if (request.getStaffRankHistories()!= null && !request.getStaffRankHistories().isEmpty()) {
      List<StaffRankHistory> rankHistoryList = request.getStaffRankHistories().stream()
          .map(rankHistory -> StaffRankHistory.builder()
              .staffId(staffId)
              .rankId(rankHistory.getRankId())
              .date(CommonUtils.convertStringToTimeStamp(rankHistory.getDate()))
              .createdBy(authName)
              .modifiedBy(authName)
              .createdDate(timestamp)
              .modifiedDate(timestamp)
              .build()).collect(Collectors.toList());
      staffRankHistoryRepository.saveAll(rankHistoryList);
    }
    if (request.getStaffWorkingHistories() != null && !request.getStaffWorkingHistories().isEmpty()) {
      List<StaffWorkingHistory> workingHistoryList = request.getStaffWorkingHistories().stream()
          .map(workingHistory -> StaffWorkingHistory.builder()
              .staffId(staffId)
              .date(CommonUtils.convertStringToTimeStamp(workingHistory.getDate()))
              .jobTitle(workingHistory.getJobTitle())
              .bonus(workingHistory.getBonus())
              .discipline(workingHistory.getDiscipline())
              .createdBy(authName)
              .modifiedBy(authName)
              .createdDate(timestamp)
              .modifiedDate(timestamp)
              .build()).collect(Collectors.toList());
      staffWorkingHistoryRepository.saveAll(workingHistoryList);
    }
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

    List<StaffRankHistoryQueryResponse> staffRankHistoryQueryResponses = staffRankHistoryRepository
        .getStaffRankHistory(staff.getId());

    List<StaffRankHistoryResponse> staffRankHistoryResponses = staffRankHistoryQueryResponses.stream()
        .map(StaffRankHistoryResponse::from).collect(Collectors.toList());
    staffResponse.setStaffRankHistories(staffRankHistoryResponses);

    // TODO: add staffAdmissions and staffWorkingHistories after, group, dept. Now i'm very tired
     return  staffResponse;
  }

  private void checkDepartment(StaffCreateRequest request) throws ActvnException {
    if (request.getGroupId() == null && (Objects.equals(request.getLevel(), LEVEL.LEVEL_2)
        || Objects.equals(request.getLevel(), LEVEL.LEVEL_1))) {
      throw new ActvnException(HttpStatus.BAD_REQUEST.value(), ExceptionMessage.GROUP_NULL);
    }
    boolean exists = departmentRepository.existsById(request.getDepartmentId());
    if (!exists) {
      throw new ActvnException(HttpStatus.BAD_REQUEST.value(),
          ExceptionMessage.DEPARTMENT_NOT_FOUND);
    }
    if (request.getGroupId() != null && !Objects.equals(request.getLevel(), LEVEL.LEVEL_3)){
      boolean existsGroup = departmentRepository.existsByIdAndParentDeptId(request.getGroupId(),
          request.getDepartmentId());
      if (!existsGroup) {
        throw new ActvnException(HttpStatus.BAD_REQUEST.value(), ExceptionMessage.GROUP_NOT_FOUND);
      }
    }
    if (Objects.equals(request.getLevel(), LEVEL.LEVEL_3)) {
      request.setGroupId(null);
    }
  }

  private void checkRank(StaffCreateRequest request) throws ActvnException {
    if (request.getRankId() != null) {
      boolean exists = rankConfigRepository.existsById(request.getRankId());
      if (!exists) {
        throw new ActvnException(HttpStatus.BAD_REQUEST.value(), ExceptionMessage.RANK_NOT_FOUND);
      }
    }
  }

  private void checkStaffAdmissions(StaffCreateRequest request) throws ActvnException {
    if (request.getStaffAdmissions() != null) {
    }
  }

  private void checkStaffRankHistories(StaffCreateRequest request) throws ActvnException {
    if (request.getStaffRankHistories() != null && !request.getStaffRankHistories().isEmpty()) {
      for (StaffRankHistoryRequest staffRankHistory : request.getStaffRankHistories()) {
        boolean exists = rankConfigRepository.existsById(staffRankHistory.getRankId());
        if (!exists) {
          throw new ActvnException(HttpStatus.BAD_REQUEST.value(), ExceptionMessage.RANK_NOT_FOUND);
        }
      }
    }
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
