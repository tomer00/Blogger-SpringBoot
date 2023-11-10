package com.tomer.blogger.repoaitories;

import com.tomer.blogger.modals.Category;
import com.tomer.blogger.modals.Post;
import com.tomer.blogger.modals.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoPost extends JpaRepository<Post,Integer> {

    Page<Post> findByUser(User user, PageRequest page);
    Page<Post> findByCategory(Category cate,PageRequest page);

    Page<Post> findByTitleContaining(String title,PageRequest page);
}
