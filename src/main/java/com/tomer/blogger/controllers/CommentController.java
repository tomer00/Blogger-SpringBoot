package com.tomer.blogger.controllers;

import com.tomer.blogger.payloads.ApiResponse;
import com.tomer.blogger.payloads.CommentDTO;
import com.tomer.blogger.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService service;


    @PostMapping
    public ResponseEntity<CommentDTO> createPost(
            @Valid @RequestBody CommentDTO postDTO,
            @RequestParam Integer postId
    ) {
        return new ResponseEntity<>(service.createComment(postDTO, postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id) {
        service.removeComment(id);
        return ResponseEntity.ok(new ApiResponse("Comment Deleted Successfully", 202));
    }
}
