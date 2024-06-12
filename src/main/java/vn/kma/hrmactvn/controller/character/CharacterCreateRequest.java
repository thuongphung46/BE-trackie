package vn.kma.hrmactvn.controller.character;


import lombok.Data;

@Data
public class CharacterCreateRequest {
    private String name;
    private String birthday;
    private String sign;
    private Integer height;
    private Integer weight;
    private String bloodType;
    private Integer shoeSize;
    private String favoriteFood;
    private String dislikes;
    private String bio;
    private String image;
}
