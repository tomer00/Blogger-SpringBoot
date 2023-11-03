package com.tomer.blogger.repoaitories;

import com.tomer.blogger.modals.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoUser extends JpaRepository<User, Integer> {
}
