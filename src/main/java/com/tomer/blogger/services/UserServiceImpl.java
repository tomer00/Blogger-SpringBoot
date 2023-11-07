package com.tomer.blogger.services;

import com.tomer.blogger.exceptions.ResourceNotFoundException;
import com.tomer.blogger.modals.User;
import com.tomer.blogger.payloads.UserDTO;
import com.tomer.blogger.repoaitories.RepoUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private RepoUser repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDTO createUser(User user) {
        var save = repo.save(user);
        return mapper.map(save, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO user, Integer userId) {
        var utemp = repo.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", " ID ", userId));

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
                new ResourceNotFoundException("User", " ID ", id)), UserDTO.class);
    }
}
