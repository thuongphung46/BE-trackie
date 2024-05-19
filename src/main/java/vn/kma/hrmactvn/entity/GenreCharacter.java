package vn.kma.hrmactvn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "tk_genre_character")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    @Column(name = "character_id", nullable = false)
    private Long characterId;
}
