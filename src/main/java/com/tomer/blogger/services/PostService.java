package com.tomer.blogger.services;


import com.tomer.blogger.payloads.PostDTO;
import com.tomer.blogger.payloads.PostResponse;

public interface PostService {


    PostDTO createPost(PostDTO post, Integer categoryId, Integer UserId);

    PostDTO updatePost(PostDTO post, Integer postId);

    void removePost(Integer postId);

    PostResponse getAllPosts(int pageSize, int pageNo, String sortBy);

    PostResponse getPostByUser(Integer userId, int pageSize, int pageNo, String sortBy);

    PostResponse getPostByCategory(Integer cateId, int pageSize, int pageNo, String sortBy);

    PostResponse searchPost(String keyWord);

    PostDTO getPost(Integer id);


}
