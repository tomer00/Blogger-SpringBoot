package com.tomer.blogger.services;

import com.tomer.blogger.exceptions.ResourceNotFoundException;
import com.tomer.blogger.modals.Post;
import com.tomer.blogger.payloads.PostDTO;
import com.tomer.blogger.repoaitories.RepoCategory;
import com.tomer.blogger.repoaitories.RepoPost;
import com.tomer.blogger.repoaitories.RepoUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private RepoPost repo;
    @Autowired
    private RepoCategory repoCate;
    @Autowired
    private RepoUser repoUser;

    @Autowired
    private ModelMapper mapper;

    @Override
    public PostDTO createPost(PostDTO post, Integer categoryId, Integer UserId) {

        var save = mapper.map(post, Post.class);

        save.setAddedDate(new Date());
        save.setUser(repoUser.findById(UserId).orElseThrow(() ->
                new ResourceNotFoundException("User", " ID ", UserId)
        ));

        save.setCategory(repoCate.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", " ID ", UserId)
        ));

        save = repo.save(save);

        return mapper.map(save, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO post, Integer postId) {
        var uTemp = repo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", " ID ", postId));

        uTemp.setTitle(post.getTitle());
        uTemp.setImage(post.getImage());
        uTemp.setContent(post.getContent());

        repo.save(uTemp);

        return post;
    }

    @Override
    public void removePost(Integer postId) {
        repo.deleteById(postId);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return repo.findAll().stream().map((i) ->
                mapper.map(i, PostDTO.class)
        ).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostByUser(Integer userId) {
        return repo.findByUser(repoUser.findById(userId).orElseThrow(() ->
                        new ResourceNotFoundException("User", " ID ", userId)
                ))
                .stream().map((i) ->
                        mapper.map(i, PostDTO.class)
                ).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getPostByCategory(Integer cateId) {
        return repo.findByCategory(repoCate.findById(cateId).orElseThrow(() ->
                        new ResourceNotFoundException("Category", " ID ", cateId)
                ))
                .stream().map((i) ->
                        mapper.map(i, PostDTO.class)
                ).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPost(String keyWord) {
//        return repo.searchBYKey(keyWord)
//                .stream().map((i) ->
//             mapper.map(i, PostDTO.class)
//        ).collect(Collectors.toList());

        return List.of();
    }

    @Override
    public PostDTO getPost(Integer id) {
        return mapper.map(repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", " ID ", id)), PostDTO.class);
    }
}
