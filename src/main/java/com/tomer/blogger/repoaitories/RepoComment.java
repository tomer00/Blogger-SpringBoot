package com.tomer.blogger.repoaitories;

import com.tomer.blogger.modals.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoComment extends JpaRepository<Comment,Integer> {
}
