package vn.kma.hrmactvn.dto;

import lombok.Data;

@Data
public class BaseResponse {
    private Integer msg_code;
    private String message;
    private Object content;
}
