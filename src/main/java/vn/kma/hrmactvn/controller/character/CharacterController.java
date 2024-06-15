package vn.kma.hrmactvn.controller.character;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.kma.hrmactvn.dto.BaseResponse;
import vn.kma.hrmactvn.entity.Character;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.service.CharacterService;

import java.util.List;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping
    public ResponseEntity<BaseResponse> createCharacter(@RequestBody CharacterCreateRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            characterService.createCharacter(request);
            response.setMsg_code(201);
            response.setMessage("Character created successfully");
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable Long id) {
        BaseResponse response = new BaseResponse();
        try {
            response.setMsg_code(200);
            response.setMessage("Success");
            response.setContent(characterService.getCharacterById(id));
        } catch (ActvnException e) {
            response.setMsg_code(401);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateCharacter(@PathVariable Long id, @RequestBody CharacterCreateRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            characterService.updateCharacter(id, request);
            response.setMsg_code(200);
            response.setMessage("Successfully updated");
        } catch (ActvnException e) {
            response.setMsg_code(401);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteCharacter(@PathVariable Long id) {
        BaseResponse response = new BaseResponse();
        try {
            characterService.deleteCharacter(id);
            response.setMsg_code(200);
            response.setMessage("Success");
        } catch (ActvnException e) {
            response.setMsg_code(401);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllCharacters() {
        BaseResponse response = new BaseResponse();
        try {
            response.setMsg_code(200);
            response.setMessage("Success");
            response.setContent(characterService.getAllCharacters());
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }
}
