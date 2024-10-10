package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public UsersService() {
    }

    public List<Users> getAllUsers() {
        return this.usersRepository.findAll();
    }

    public Users getUserById(Long id) {
        return (Users)this.usersRepository.findById(id).orElse((Users) null);
    }

    public Users createUser(Users users) {
        return (Users)this.usersRepository.save(users);
    }

    public Users getUserByEmailAndPassword(String email, String password) {
        return this.usersRepository.findByEmailAndPassword(email, password);
    }

    public void deleteUser(Long id) {
        this.usersRepository.deleteById(id);
    }

    public Users updateUser(Long id, Users users) {
        Users user = (Users)this.usersRepository.findById(id).orElse((Users) null);
        if (user != null) {
            user.setName(users.getName());
            user.setEmail(users.getEmail());
            user.setPassword(users.getPassword());
            user.setRole(users.getRole());
            user.setAddress(users.getAddress());
            return (Users)this.usersRepository.save(user);
        } else {
            return (Users) null;
        }
    }

    public Users updateUserPassword(Long id, String password) {
        Users user = (Users)this.usersRepository.findById(id).orElse((Users) null);
        if (user != null) {
            user.setPassword(password);
            return (Users)this.usersRepository.save(user);
        } else {
            return (Users) null;
        }
    }
}