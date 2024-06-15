package vn.kma.hrmactvn.controller.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.kma.hrmactvn.dto.BaseResponse;
import vn.kma.hrmactvn.entity.Genre;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.service.GenreService;
import vn.kma.hrmactvn.controller.genre.dto.GenreResponse;

import java.util.List;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/home")
    public ResponseEntity<BaseResponse> getHome() {
        BaseResponse response = new BaseResponse();
        try {

            response.setMsg_code(200);
            response.setMessage("Success");
            response.setContent(genreService.getHome());
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
            GenreResponse genreResponse = genreService.getById(id);
            response.setMsg_code(200);
            response.setMessage("Success");
            response.setContent(genreResponse);
        } catch (ActvnException e) {
            response.setMsg_code(401);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createGenre(@RequestBody GenreCreateRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            genreService.createGenre(request);
            response.setMsg_code(201);
            response.setMessage("Genre created successfully");
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updateGenre(@PathVariable Long id, @RequestBody GenreCreateRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            genreService.update(id, request);
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
    public ResponseEntity<BaseResponse> deleteGenre(@PathVariable Long id) {
        BaseResponse response = new BaseResponse();
        try {
            genreService.delete(id);
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
    public ResponseEntity<BaseResponse> getAllGenres() {
        BaseResponse response = new BaseResponse();
        try {
            genreService.getAllGenres();
            response.setMsg_code(200);
            response.setMessage("Success");
            response.setContent(genreService.getAllGenres());
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xảy ra");
        }
        return ResponseEntity.ok(response);
    }
}
