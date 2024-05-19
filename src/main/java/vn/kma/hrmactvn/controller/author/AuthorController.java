package vn.kma.hrmactvn.controller.author;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.service.AuthorService;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) throws ActvnException {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

}
