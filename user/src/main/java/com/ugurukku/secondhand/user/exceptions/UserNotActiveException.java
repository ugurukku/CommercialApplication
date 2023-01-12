package com.ugurukku.secondhand.user.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotActiveException extends RuntimeException {

    private static final String message = "User is not active!";

    public UserNotActiveException() {
        super(message);
    }

    public UserNotActiveException(String message) {
        super(message);
    }
}
