package com.tomer.blogger.config;

import com.tomer.blogger.exceptions.ResourceNotFoundException;
import com.tomer.blogger.repoaitories.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class DBUserService implements UserDetailsService {

    @Autowired
    private RepoUser repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByEmail(username).orElseThrow(() ->
                new ResourceNotFoundException("User", " Email ", username));
    }
}
