package vn.kma.hrmactvn.controller.post.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostCreateRequest {
    private String name;
    private String description;
    private String synopsis;
    private String image;
    private List<Long> genreIds;
    private List<Long> authorIds;
    private List<Long> characterIds;
}
