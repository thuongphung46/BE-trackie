package vn.kma.hrmactvn.constant;

public interface ApplicationConstant {
  public interface SECURITY_CONFIG {

    String[] DISABLE_AUTHORIZE = {
        "/auth/**",
        "/public/**",
        "/error/**",
    };
  }

  interface ADMISSION_TYPE {
    String UNIONS = "UNIONS"; // Đoàn

    String PARTY = "PARTY"; // Đảng
  }

  interface LEVEL {

    String LEVEL_1 = "LEVEL_1"; // Cấp 1

    String LEVEL_2 = "LEVEL_2"; // Cấp 2

    String LEVEL_3 = "LEVEL_3"; // Cấp 3
  }
}
