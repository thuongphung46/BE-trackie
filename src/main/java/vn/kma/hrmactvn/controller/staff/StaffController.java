package vn.kma.hrmactvn.controller.staff;


import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.kma.hrmactvn.controller.staff.dto.StaffCreateRequest;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.service.StaffService;

@RestController
@RequestMapping("/staffs")
@RequiredArgsConstructor
public class StaffController {

  private final StaffService staffService;

  @PostMapping
  public ResponseEntity createStaff(@RequestBody @Valid StaffCreateRequest request)
      throws ActvnException {
    return ResponseEntity.ok(staffService.create(request));
  }

  @GetMapping("/me")
  public ResponseEntity getMe() throws ActvnException {
    return ResponseEntity.ok(staffService.getMe());
  }

}
