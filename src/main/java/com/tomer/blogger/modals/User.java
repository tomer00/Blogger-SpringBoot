package com.tomer.blogger.modals;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "user_name", length = 80)
    @NotNull
    private String name;
    @Column(name = "email", length = 140)
    @NotNull
    @Email(message = "Please Use a Valid Email Address")
    private String email;
    @Column(name = "pass", nullable = false)
    @NotNull
    @Size(min = 4, max = 12, message = "Password must be min of 4 and max of 12 chars")
    private String password;
    @Column(name = "about", columnDefinition = "TEXT")
    private String about;
}
