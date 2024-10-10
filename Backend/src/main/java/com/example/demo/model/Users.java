package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)  // Utilisation de EnumType.STRING pour mapper les valeurs en tant que chaînes de caractères
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "address")
    private String address;

    // Enumération Role
    public enum Role {
        MANAGER,
        AGENT,
        CLIENT
    }

    // Constructeurs
    public Users() {
    }

    public Users(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Role.CLIENT; // Initialisation du rôle à CLIENT
        this.address = address;
    }

    // Getters et setters
    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
