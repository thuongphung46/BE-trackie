package vn.kma.hrmactvn.error;


import lombok.Data;

@Data
public class ActvnException extends Exception {
  private int statusCode;

  public ActvnException(int statusCode, String message) {
    super(message);
    this.statusCode = statusCode;
  }


}
