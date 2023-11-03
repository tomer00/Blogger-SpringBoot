package com.tomer.blogger.services;

import com.tomer.blogger.payloads.UserDTO;
import com.tomer.blogger.repoaitories.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private RepoUser repo;

    @Override
    public UserDTO createUser(UserDTO user) {
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO user, Integer userId) {
        return null;
    }

    @Override
    public boolean removeUser(Integer userId) {
        return false;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public UserDTO getUser(Integer id) {
        return null;
    }
}
