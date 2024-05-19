package vn.kma.hrmactvn.controller.genre;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
