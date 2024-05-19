package vn.kma.hrmactvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.kma.hrmactvn.entity.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByIdIn(List<Long> postIds);


    @Query(
            value = "SELECT * FROM tk_post WHERE (:name IS NULL OR name LIKE %:name%)",
            nativeQuery = true
    )
    List<Post> findAllByNameLike(@Param("name") String query);
}
