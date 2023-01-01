package com.ugurukku.secondhand.dto;

import com.ugurukku.secondhand.models.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    private final UserDetailsDtoConverter detailsDtoConverter;

    public UserDtoConverter(UserDetailsDtoConverter detailsDtoConverter) {
        this.detailsDtoConverter = detailsDtoConverter;
    }

    public UserDto convert(Users from) {
        return new UserDto(from.getEmail(),
                from.getFirstName(),
                from.getLastName(),
                from.getPostCode(),
                from.getActive(),
                detailsDtoConverter.convert(new ArrayList<>(from.getUserDetailsSet())));
    }

    public List<UserDto> convert(List<Users> fromList) {
        return fromList
                .stream()
                .map(this::convert).collect(Collectors.toList());
    }

}
