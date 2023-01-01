package com.ugurukku.secondhand.dto;

import com.ugurukku.secondhand.models.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convert(Users from) {
        return new UserDto(from.getEmail(), from.getFirstName(), from.getLastName(), from.getPostCode(), from.getActive());
    }

    public List<UserDto> convert(List<Users> fromList) {
        return fromList
                .stream()
                .map(from -> new UserDto(
                        from.getEmail(),
                        from.getFirstName(),
                        from.getLastName(),
                        from.getPostCode(),
                        from.getActive())).collect(Collectors.toList());
    }

}
