package com.ugurukku.secondhand.dto;

import com.ugurukku.secondhand.models.UserInformation;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto convert(UserInformation from) {
        return new UserDto(from.getEmail(), from.getFirstName(), from.getLastName(), from.getPostCode(),from.getActive());
    }

}
