package vn.kma.hrmactvn.controller.genre.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.kma.hrmactvn.controller.post.dto.PostResponse;
import vn.kma.hrmactvn.entity.Genre;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreResponse {
    private Long id;
    private String name;
    private List<PostResponse> posts;

    public static GenreResponse from(Genre genre) {
        return GenreResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
