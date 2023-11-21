package com.tomer.blogger.modals;

import com.tomer.blogger.config.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

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


    @Column(name = "pass", columnDefinition = "TEXT", nullable = false)
    @NotNull
    @Size(min = 4, message = "Password must be min of 4 and max of 12 chars")
    private String password;


    @Column(name = "about", columnDefinition = "TEXT")
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;


    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
