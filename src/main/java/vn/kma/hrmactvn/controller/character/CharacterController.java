package vn.kma.hrmactvn.controller.character;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.service.CharacterService;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws ActvnException {
        return ResponseEntity.ok(characterService.getCharacterById(id));
    }
}
