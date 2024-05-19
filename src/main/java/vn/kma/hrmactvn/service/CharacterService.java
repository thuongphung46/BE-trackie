package vn.kma.hrmactvn.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.kma.hrmactvn.entity.Character;
import vn.kma.hrmactvn.entity.Post;
import vn.kma.hrmactvn.entity.PostCharacter;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.repository.CharacterRepository;
import vn.kma.hrmactvn.repository.PostCharacterRepository;
import vn.kma.hrmactvn.repository.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final PostRepository postRepository;
    private final PostCharacterRepository postCharacterRepository;

    public Character getCharacterById(Long id) throws ActvnException {
        Character character = characterRepository.findById(id).orElse(null);
        if (character == null) {
           throw new ActvnException(404, "Character not found");
        }
        List<Long> postIds = postCharacterRepository.findAllByCharacterId(character.getId()).stream().map(PostCharacter::getPostId).collect(Collectors.toList());
        List<Post> posts = postRepository.findAllByIdIn(postIds);
        character.setPosts(posts);
        return character;
    }
}
