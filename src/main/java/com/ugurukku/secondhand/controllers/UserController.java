package com.ugurukku.secondhand.controllers;

import com.ugurukku.secondhand.dto.CreateUserRequest;
import com.ugurukku.secondhand.dto.UpdateUserRequest;
import com.ugurukku.secondhand.dto.UserDto;
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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserById(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok(userService.getByEmail(email));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.add(createUserRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") String email, @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.update(updateUserRequest, email));
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") Long id) {
        userService.deactivate(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateUser(@PathVariable("id") Long id) {
        userService.activate(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }


}
