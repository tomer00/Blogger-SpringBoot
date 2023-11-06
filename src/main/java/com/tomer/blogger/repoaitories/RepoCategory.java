package com.tomer.blogger.repoaitories;

import com.tomer.blogger.modals.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoCategory extends JpaRepository<Category, Integer> {
}
