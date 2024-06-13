package vn.kma.hrmactvn.controller.post;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.kma.hrmactvn.controller.post.dto.PostCreateRequest;
import vn.kma.hrmactvn.controller.post.dto.PostResponse;
import vn.kma.hrmactvn.entity.Post;
import vn.kma.hrmactvn.error.ActvnException;
import vn.kma.hrmactvn.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity getPostById(@PathVariable Long id) throws ActvnException {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("/most-popular")
    public ResponseEntity getMostPopularPosts() {
        return ResponseEntity.ok(postService.getMostPopularPosts());
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts(@RequestParam(required = false) String query) {
        return ResponseEntity.ok(postService.getPosts(query));
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostCreateRequest request) {
        return ResponseEntity.ok(postService.createPost(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostCreateRequest request) throws ActvnException {
        return ResponseEntity.ok(postService.update(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) throws ActvnException {
        postService.delete(id);
        return ResponseEntity.ok().build();
    }

}
