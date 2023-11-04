package com.tomer.blogger.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    private int ID;

    @NotEmpty
    @Size(min = 4, message = "Name must be 4 chars minimum")
    private String name;
    @Email(message = "Please Use a Valid Email Address")
    private String email;
    private String about;
}
