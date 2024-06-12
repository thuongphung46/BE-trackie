package vn.kma.hrmactvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.kma.hrmactvn.entity.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByIdIn(List<Long> authorIds);

    Author findFirstById(Long id);
}
