package com.tomer.blogger.services;

import com.tomer.blogger.payloads.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user, Integer userId);

    boolean removeUser(Integer userId);

    List<UserDTO> getAllUsers();

    UserDTO getUser(Integer id);

}
