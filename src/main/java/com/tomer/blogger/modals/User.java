package com.tomer.blogger.modals;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = "user_name", nullable = false, length = 80)
    private String name;
    @Column(name = "email", nullable = false, length = 140)
    private String email;
    @Column(name = "pass", nullable = false)
    private String password;
    @Column(name = "about", columnDefinition = "TEXT")
    private String about;
}
