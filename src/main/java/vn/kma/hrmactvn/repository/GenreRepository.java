package vn.kma.hrmactvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.kma.hrmactvn.entity.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findAllByIdIn(List<Long> genreIds);
}
