package vn.kma.hrmactvn.controller.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.kma.hrmactvn.controller.auth.dto.LoginRequest;
import vn.kma.hrmactvn.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;


  @PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginRequest request){
    return authService.login(request);
  }

}
