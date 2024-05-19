package vn.kma.hrmactvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.kma.hrmactvn.entity.Character;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findAllByIdIn(List<Long> characterIds);
}
