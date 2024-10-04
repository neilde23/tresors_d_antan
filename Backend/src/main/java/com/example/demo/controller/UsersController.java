package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api"})
public class UsersController {
    @Autowired
    private UsersService usersService;

    public UsersController() {
    }

    @GetMapping({"/users"})
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            List<Users> users = this.usersService.getAllUsers();
            if (users.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"/users/{id}"})
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        try {
            Users user = this.usersService.getUserById(id);
            if (user != null) {
                return new ResponseEntity(user, HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping({"/users"})
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        try {
            Users newUser = this.usersService.createUser(users);
            return new ResponseEntity(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping({"/users/{id}"})
    public ResponseEntity deleteUser(@PathVariable Long id) {
        try {
            this.usersService.deleteUser(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping({"/users/{id}"})
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users users) {
        try {
            Users updatedUser = this.usersService.updateUser(id, users);
            if (updatedUser != null) {
                return new ResponseEntity(updatedUser, HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping({"/users/{id}/password"})
    public ResponseEntity<Users> updateUserPassword(@PathVariable Long id, @RequestBody String password) {
        try {
            Users updatedUser = this.usersService.updateUserPassword(id, password);
            if (updatedUser != null) {
                return new ResponseEntity(updatedUser, HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
