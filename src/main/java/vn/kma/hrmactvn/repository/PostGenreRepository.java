package vn.kma.hrmactvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.kma.hrmactvn.entity.Genre;
import vn.kma.hrmactvn.entity.PostGenre;

import java.util.List;

public interface PostGenreRepository extends JpaRepository<PostGenre, Long> {

    List<PostGenre> findAllByGenreId(Long genreId);

    List<PostGenre> findAllByPostId(Long postId);

    void deleteAllByPostId(Long id);
}
