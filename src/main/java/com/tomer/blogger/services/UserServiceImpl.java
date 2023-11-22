package com.tomer.blogger.services;

import com.tomer.blogger.config.JWTHelper;
import com.tomer.blogger.config.Role;
import com.tomer.blogger.exceptions.ResourceNotFoundException;
import com.tomer.blogger.modals.User;
import com.tomer.blogger.payloads.AuthResponse;
import com.tomer.blogger.payloads.UserDTO;
import com.tomer.blogger.repoaitories.RepoUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private RepoUser repo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JWTHelper jwtHelper;

    @Override
    public UserDTO createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        var save = repo.save(user);
        return mapper.map(save, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO user, Integer userId) {
        var utemp = repo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", " ID ", userId.toString()));

        utemp.setAbout(user.getAbout());
        utemp.setName(user.getName());

        repo.save(utemp);

        return user;
    }

    @Override
    public void removeUser(Integer userId) {
        repo.deleteById(userId);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return repo.findAll().stream().map((i) -> mapper.map(i, UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUser(Integer id) {
        return mapper.map(repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", " ID ", id.toString())), UserDTO.class);
    }

    @Override
    public UserDTO updateUserToAdmin(Integer id) {
      var u =  repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", " ID ", id.toString()));

      u.setRole(Role.ADMIN);
      repo.save(u);
        return mapper.map(u, UserDTO.class);
    }

    @Override
    public AuthResponse getToken(String user, String pass) {

       var us= repo.findByEmail(user)
               .orElseThrow(() ->
                        new ResourceNotFoundException("User", " ID ", user));
        System.out.println(us.getPassword());
        return new AuthResponse(jwtHelper.generateToken(us),"DONE",200);
    }
}
