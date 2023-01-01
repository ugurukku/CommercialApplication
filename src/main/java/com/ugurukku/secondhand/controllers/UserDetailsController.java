package com.ugurukku.secondhand.controllers;

import com.ugurukku.secondhand.dto.CreateUserDetailsRequest;
import com.ugurukku.secondhand.dto.UpdateUserDetailsRequest;
import com.ugurukku.secondhand.dto.UserDetailsDto;
import com.ugurukku.secondhand.services.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/userDetails")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping
    public ResponseEntity<UserDetailsDto> createUserDetails(@RequestBody CreateUserDetailsRequest request) {
        return ResponseEntity.ok(userDetailsService.createUserDetails(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailsDto> updateUserDetails(@PathVariable("id") Long id,@RequestBody UpdateUserDetailsRequest request) {
        return ResponseEntity.ok(userDetailsService.updateUserDetails(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable("id") Long id){
        userDetailsService.deleteUserDetails(id);
        return ResponseEntity.ok().build();
    }
}
