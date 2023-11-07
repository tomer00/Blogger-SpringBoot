package com.tomer.blogger.services;


import com.tomer.blogger.payloads.PostDTO;

import java.util.List;

public interface PostService {


    PostDTO createPost(PostDTO post,Integer categoryId,Integer UserId);

    PostDTO updatePost(PostDTO post, Integer postId);

    void removePost(Integer postId);

    List<PostDTO> getAllPosts();
    List<PostDTO> getPostByUser(Integer userId);
    List<PostDTO> getPostByCategory(Integer cateId);
    List<PostDTO> searchPost(String keyWord);

    PostDTO getPost(Integer id);


}
