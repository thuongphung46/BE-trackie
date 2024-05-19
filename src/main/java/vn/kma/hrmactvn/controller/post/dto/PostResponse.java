package vn.kma.hrmactvn.controller.post.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.kma.hrmactvn.entity.Author;
import vn.kma.hrmactvn.entity.Character;
import vn.kma.hrmactvn.entity.Genre;
import vn.kma.hrmactvn.entity.Post;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
    private Long id;
    private String name;
    private String synopsis;
    private String image;
    private Float rating;
    private Long reviewCount;
    private List<Author> author;
    private List<Genre> genres;
    private List<Character> characters;


    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .name(post.getName())
                .synopsis(post.getSynopsis())
                .image(post.getImage())
                .build();
    }
}
