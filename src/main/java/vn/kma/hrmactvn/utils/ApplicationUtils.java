package vn.kma.hrmactvn.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import vn.kma.hrmactvn.authentication.UserDetailsImpl;

public class ApplicationUtils {

  public static UserDetailsImpl currentUser() {
    try {
      return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } catch (Exception e) {
      return null;
    }
  }

  public static Long currentUserId() {
    try {
      return currentUser().getId();
    } catch (Exception e) {
      return null;
    }
  }



}
