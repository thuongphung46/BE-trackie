package vn.kma.hrmactvn.controller.author;

import lombok.Data;

@Data
public class AuthorCreateRequest {
    private String name;
    private String birthday;
    private String place;
    private String bio;
    private String image;

}
