package com.tomer.blogger.repoaitories;

import com.tomer.blogger.modals.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepoUser extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
