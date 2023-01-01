package com.ugurukku.secondhand.controllers;

import com.ugurukku.secondhand.dto.CreateUserRequest;
import com.ugurukku.secondhand.dto.UpdateUserRequest;
import com.ugurukku.secondhand.dto.UserDto;
import com.ugurukku.secondhand.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UsersService usersService;


    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAll());
    }

    @GetMapping
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok(usersService.getByEmail(email));
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(usersService.createUser(createUserRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") String email, @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(usersService.update(updateUserRequest, email));
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") Long id) {
        usersService.deactivate(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateUser(@PathVariable("id") Long id) {
        usersService.activate(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        usersService.delete(id);
        return ResponseEntity.ok().build();
    }


}
