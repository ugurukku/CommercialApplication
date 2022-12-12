package com.ugurukku.secondhand.controllers;

import com.ugurukku.secondhand.dto.CreateUserRequest;
import com.ugurukku.secondhand.models.User;
import com.ugurukku.secondhand.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.add(createUserRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.update(updateUserRequest,id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> deactivateUser(@PathVariable("id") Long id) {
    return ResponseEntity.ok(userService.deactivate(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.delete(id));
    }


}
