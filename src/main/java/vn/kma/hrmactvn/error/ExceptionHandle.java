package vn.kma.hrmactvn.error;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandle {

  @ExceptionHandler(ActvnException.class)
  public ResponseEntity handleCustomException(ActvnException e) {
    e.printStackTrace();
    return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity handleUnwantedException(Exception e) {
    log.error("Có lỗi xảy ra", e);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionMessage.SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    String message = e.getBindingResult().getFieldError() == null ? ExceptionMessage.SERVER_ERROR : e.getBindingResult().getFieldError().getDefaultMessage();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
  }

}
