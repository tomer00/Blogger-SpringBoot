package com.tomer.blogger.controllers;

import com.tomer.blogger.payloads.ApiResponse;
import com.tomer.blogger.payloads.PostDTO;
import com.tomer.blogger.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getByUser(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String q
    ) {
        if (q!=null) return new ResponseEntity<>(service.searchPost(q), HttpStatus.OK);
        else if (userId!=null) return new ResponseEntity<>(service.getPostByUser(userId), HttpStatus.OK);
        else return new ResponseEntity<>(List.of(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/cate/{id}")
    public ResponseEntity<List<PostDTO>> getByCategory(
            @PathVariable Integer id
    ) {
        return new ResponseEntity<>(service.getPostByCategory(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createUser(
            @Valid @RequestBody PostDTO postDTO,
            @RequestParam Integer cateId,
            @RequestParam Integer userId
    ) {
        return new ResponseEntity<>(service.createPost(postDTO, cateId, userId), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO post, @PathVariable Integer id) {
        return new ResponseEntity<>(service.updatePost(post, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getPost(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(service.getAllPosts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id) {
        service.removePost(id);
        return ResponseEntity.ok(new ApiResponse("Post Deleted Successfully", 202));
    }
}
