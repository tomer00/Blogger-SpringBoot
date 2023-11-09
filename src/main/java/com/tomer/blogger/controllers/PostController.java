package com.tomer.blogger.controllers;

import com.tomer.blogger.payloads.ApiResponse;
import com.tomer.blogger.payloads.PostDTO;
import com.tomer.blogger.payloads.PostResponse;
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
    public ResponseEntity<PostResponse> getByUser(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String q,
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "6",required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "ID",required = false) String sortBy
    ) {
        if (q!=null) return new ResponseEntity<>(service.searchPost(q), HttpStatus.OK);
        else if (userId!=null) return new ResponseEntity<>(service.getPostByUser(userId,pageSize,pageNo,sortBy), HttpStatus.OK);
        else return new ResponseEntity<>(new PostResponse(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/cate/{id}")
    public ResponseEntity<PostResponse> getByCategory(
            @PathVariable Integer id,
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "6",required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "ID",required = false) String sortBy
    ) {
        return new ResponseEntity<>(service.getPostByCategory(id,pageSize,pageNo,sortBy), HttpStatus.OK);
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
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "6",required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "ID",required = false) String sortBy
    ) {
        return ResponseEntity.ok(service.getAllPosts(pageSize,pageNo,sortBy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id) {
        service.removePost(id);
        return ResponseEntity.ok(new ApiResponse("Post Deleted Successfully", 202));
    }
}
