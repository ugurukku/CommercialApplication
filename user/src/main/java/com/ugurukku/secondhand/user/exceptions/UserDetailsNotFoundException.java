package com.ugurukku.secondhand.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserDetailsNotFoundException extends RuntimeException{

    public UserDetailsNotFoundException(String message) {
        super(message);
    }
}
