package com.ugurukku.secondhand.dto;

import com.ugurukku.secondhand.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto convert(User from) {
        return new UserDto(from.getEmail(), from.getFirstName(), from.getLastName(), from.getPostCode());
    }

}
