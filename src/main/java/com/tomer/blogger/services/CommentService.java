package com.tomer.blogger.services;

import com.tomer.blogger.payloads.CommentDTO;

public interface CommentService {

    CommentDTO createComment(CommentDTO comment, Integer postId);

    void removeComment(Integer commentId);
}
