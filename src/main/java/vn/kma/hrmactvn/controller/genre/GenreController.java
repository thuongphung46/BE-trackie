package vn.kma.hrmactvn.controller.genre;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.service.GenreService;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/home")
    public ResponseEntity getHome() {
        return ResponseEntity.ok(genreService.getHome());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) throws ActvnException {
        return ResponseEntity.ok(genreService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> createGenre(@RequestBody GenreCreateRequest request) {
        return ResponseEntity.ok(genreService.createGenre(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable Long id, @RequestBody GenreCreateRequest request) throws ActvnException {
        return ResponseEntity.ok(genreService.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long id) throws ActvnException {
        genreService.delete(id);
        return ResponseEntity.ok().build();
    }

}
