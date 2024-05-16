package vn.kma.hrmactvn.error;

public interface ExceptionMessage {

  String USER_NAME_NULL = "Username không được để trống";

  String PASSWORD_NULL = "Mật khẩu không được để trống";

  String LEVEL_NULL = "Level không được để trống";

  String SERVER_ERROR = "Có lỗi xảy ra";

  String ACTIVE_NULL = "Active không được để trống";

  String USERNAME_EXISTS = "Username đã tồn tại";

  String PHONE_NUMBER_NULL = "Số điện thoại không được để trống";

  String FULL_NAME_NULL = "Họ và tên không được để trống";

  String GENDER_NULL = "Giới tính không được để trống";

  String JOB_TITLE_NULL = "Chức vụ không được để trống";

  String DEPARTMENT_ID_NULL = "Phòng ban không được để trống";

  String DATE_NULL = "Ngày không được để trống";

  String DEPARTMENT_NOT_FOUND = "Không tìm thấy phòng ban";

  String GROUP_NOT_FOUND = "Không tìm thấy nhóm";

  String GROUP_NULL = "Nhóm không được để trống";

  String RANK_NOT_FOUND = "Không tìm thấy cấp/hàm";

  String STAFF_NOT_FOUND = "Không tìm thấy nhân viên";

  String STAFF_INFO_NOT_FOUND = "Không tìm thấy thông tin nhân viên";
}
