package vn.kma.hrmactvn.service;


import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.kma.hrmactvn.authentication.JwtUtil;
import vn.kma.hrmactvn.controller.auth.dto.JwtAuthenticationResponse;
import vn.kma.hrmactvn.controller.auth.dto.LoginRequest;
import vn.kma.hrmactvn.entity.Staff;
import vn.kma.hrmactvn.repository.StaffRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final StaffRepository staffRepository;
  private final JwtUtil jwtUtil;
  private final AuthenticationManager authenticationManager;

  public ResponseEntity login(LoginRequest request){
    String username = request.getUsername();

    Optional<Staff> staff = staffRepository.findByUsernameAndActive(username,true);
    if(staff.isEmpty()){
      return ResponseEntity.badRequest().body("Tên đăng nhập hoặc mật khẩu không đúng");
    }
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body("Tên đăng nhập hoặc mật khẩu không đúng");
    }
    String token = jwtUtil.createToken(staff.get());
    return ResponseEntity.ok(JwtAuthenticationResponse.builder().accessToken(token).type("Bearer").requireUpdateProfile(false).build());
  }

}
