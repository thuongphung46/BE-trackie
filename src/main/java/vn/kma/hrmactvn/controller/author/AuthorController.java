package vn.kma.hrmactvn.controller.author;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.kma.hrmactvn.entity.Author;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) throws ActvnException {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorCreateRequest request) {
        return ResponseEntity.ok(authorService.createAuthor(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody AuthorCreateRequest request) throws ActvnException {
        return ResponseEntity.ok(authorService.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) throws ActvnException {
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

}
