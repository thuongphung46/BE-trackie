package vn.kma.hrmactvn.controller.character;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.kma.hrmactvn.entity.Character;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.service.CharacterService;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping
    public ResponseEntity<Character> createCharacter(@RequestBody CharacterCreateRequest request) {
        return ResponseEntity.ok(characterService.createCharacter(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws ActvnException {
        return ResponseEntity.ok(characterService.getCharacterById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateCharacter(@PathVariable Long id, @RequestBody CharacterCreateRequest request) throws ActvnException {
        return ResponseEntity.ok(characterService.updateCharacter(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCharacter(@PathVariable Long id) throws ActvnException {
        characterService.deleteCharacter(id);
        return ResponseEntity.ok().build();
    }
}
