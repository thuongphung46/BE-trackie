package vn.kma.hrmactvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.kma.hrmactvn.entity.PostCharacter;

import java.util.Collection;
import java.util.List;

public interface PostCharacterRepository extends JpaRepository<PostCharacter, Long> {
    List<PostCharacter> findAllByPostId(Long id);

    List<PostCharacter> findAllByCharacterId(Long id);
}
