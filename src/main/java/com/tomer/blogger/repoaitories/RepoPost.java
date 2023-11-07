package com.tomer.blogger.repoaitories;

import com.tomer.blogger.modals.Category;
import com.tomer.blogger.modals.Post;
import com.tomer.blogger.modals.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepoPost extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category cate);

//    @Query("SELECT * from `posts` where title LIKE '% :key %'")
//    List<Post> searchBYKey(String key);
}
