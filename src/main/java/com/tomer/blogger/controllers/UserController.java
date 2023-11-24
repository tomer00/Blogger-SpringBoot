package com.tomer.blogger.controllers;


import com.tomer.blogger.modals.User;
import com.tomer.blogger.payloads.ApiResponse;
import com.tomer.blogger.payloads.AuthResponse;
import com.tomer.blogger.payloads.UserDTO;
import com.tomer.blogger.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(service.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO user, @PathVariable Integer id) {
        return new ResponseEntity<>(service.updateUser(user, id), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getUser(id));
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer id) {
        return ResponseEntity.ok(service.updateUserToAdmin(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        service.removeUser(id);
        return ResponseEntity.ok(new ApiResponse("User Deleted Successfully", 202));
    }

}
