package vn.kma.hrmactvn.controller.author;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.kma.hrmactvn.dto.BaseResponse;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getAuthorById(@PathVariable Long id) {
        BaseResponse response = new BaseResponse();
        try {

            response.setMsg_code(200);
            response.setMessage("Successfully");
            response.setContent( authorService.getAuthorById(id));
        } catch (ActvnException e) {
            response.setMsg_code(-1);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createAuthor(@RequestBody AuthorCreateRequest request) {
        BaseResponse response = new BaseResponse();
        try {

            response.setMsg_code(200);
            response.setMessage("Successfully");
            response.setContent( authorService.createAuthor(request));
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateAuthor(@PathVariable Long id, @RequestBody AuthorCreateRequest request) {
        BaseResponse response = new BaseResponse();
        try {

            response.setMsg_code(200);
            response.setMessage("Successfully");
            response.setContent(authorService.update(id, request));
        } catch (ActvnException e) {
            response.setMsg_code(-1);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteAuthor(@PathVariable Long id) {
        BaseResponse response = new BaseResponse();
        try {
            authorService.delete(id);
            response.setMsg_code(200);
            response.setMessage("Success");
        } catch (ActvnException e) {
            response.setMsg_code(-1);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getAllAuthors() {
        BaseResponse response = new BaseResponse();
        try {

            response.setMsg_code(200);
            response.setMessage("Success");
            response.setContent(authorService.getAllAuthors());
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }
}
