package vn.kma.hrmactvn.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.kma.hrmactvn.controller.post.dto.PostCreateRequest;
import vn.kma.hrmactvn.controller.post.dto.PostResponse;
import vn.kma.hrmactvn.dto.BaseResponse;
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
    public ResponseEntity<BaseResponse> getPostById(@PathVariable Long id) {
        BaseResponse response = new BaseResponse();
        try {
            PostResponse postResponse = postService.getPostById(id);
            response.setMsg_code(200);
            response.setMessage("Success");
            response.setContent(postResponse);
        } catch (ActvnException e) {
            response.setMsg_code(-1);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xẩy ra");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/most-popular")
    public ResponseEntity<BaseResponse> getMostPopularPosts() {
        BaseResponse response = new BaseResponse();
        try {
            List<PostResponse> popularPosts = postService.getMostPopularPosts();
            response.setMsg_code(200);
            response.setMessage("Success");
            response.setContent(popularPosts);
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xẩy ra");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> getPosts(@RequestParam(required = false) String query) {
        BaseResponse response = new BaseResponse();
        try {
            List<PostResponse> posts = postService.getPosts(query);
            response.setMsg_code(200);
            response.setMessage("Success");
            response.setContent(posts);
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xẩy ra");
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createPost(@RequestBody PostCreateRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            postService.createPost(request);
            response.setMsg_code(200);
            response.setMessage("Successfully");
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xẩy ra");
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> updatePost(@PathVariable Long id, @RequestBody PostCreateRequest request) {
        BaseResponse response = new BaseResponse();
        try {
            postService.update(id, request);
            response.setMsg_code(200);
            response.setMessage("successfully");
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xẩy ra");
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deletePost(@PathVariable Long id) {
        BaseResponse response = new BaseResponse();
        try {
            postService.delete(id);
            response.setMsg_code(200);
            response.setMessage("Success");
        } catch (ActvnException e) {
            response.setMsg_code(-1);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setMsg_code(400);
            response.setMessage("Có lỗi xẩy ra");
        }
        return ResponseEntity.ok(response);
    }
}
