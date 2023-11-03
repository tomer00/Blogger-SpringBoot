package com.tomer.blogger.payloads;


import lombok.Data;

@Data
public class UserDTO {

    private int ID;

    private String name;
    private String email;
    private String password;
    private String about;
}
