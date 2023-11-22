package com.tomer.blogger.services;

import com.tomer.blogger.modals.User;
import com.tomer.blogger.payloads.AuthResponse;
import com.tomer.blogger.payloads.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(User user);

    UserDTO updateUser(UserDTO user, Integer userId);

    void removeUser(Integer userId);

    List<UserDTO> getAllUsers();

    UserDTO getUser(Integer id);

    UserDTO updateUserToAdmin(Integer id);

    AuthResponse getToken(String user, String pass);
}
