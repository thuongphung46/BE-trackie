package vn.kma.hrmactvn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "tk_post_genre")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;
}
