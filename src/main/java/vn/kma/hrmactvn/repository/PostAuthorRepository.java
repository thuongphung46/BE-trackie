package vn.kma.hrmactvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.kma.hrmactvn.entity.PostAuthor;

import java.util.Collection;
import java.util.List;

public interface PostAuthorRepository extends JpaRepository<PostAuthor, Long> {
    List<PostAuthor> findAllByPostId(Long id);

    List<PostAuthor> findAllByAuthorId(Long id);

    void deleteAllByPostId(Long id);
}
