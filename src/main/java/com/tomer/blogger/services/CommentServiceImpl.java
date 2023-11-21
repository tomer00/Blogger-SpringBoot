package com.tomer.blogger.services;

import com.tomer.blogger.exceptions.ResourceNotFoundException;
import com.tomer.blogger.modals.Comment;
import com.tomer.blogger.modals.Post;
import com.tomer.blogger.payloads.CommentDTO;
import com.tomer.blogger.repoaitories.RepoComment;
import com.tomer.blogger.repoaitories.RepoPost;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private RepoComment repo;

    @Autowired
    private RepoPost postRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public CommentDTO createComment(CommentDTO comment, Integer postId) {

        Post p = postRepo.findById(postId)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Comment", " ID ", postId.toString()
                        )
                );

        var t = mapper.map(comment, Comment.class);
        t.setPost(p);

        return mapper.map(repo.save(t), CommentDTO.class);
    }

    @Override
    public void removeComment(Integer commentId) {
        repo.deleteById(commentId);
    }
}
