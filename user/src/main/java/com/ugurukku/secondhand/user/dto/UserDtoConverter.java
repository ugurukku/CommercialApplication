package com.ugurukku.secondhand.user.dto;

import com.ugurukku.secondhand.user.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {


    private final UserDetailsDtoConverter userDetailsDtoConverter;

    public UserDtoConverter() {
        this.userDetailsDtoConverter = new UserDetailsDtoConverterImpl();
    }


    public UserDto convert(Users from) {
        return new UserDto(from.getEmail(),
                from.getFirstName(),
                from.getLastName(),
                from.getPostCode(),
                from.getActive(),
                from.getUserDetailsSet() != null ? userDetailsDtoConverter.convert(new ArrayList<>(from.getUserDetailsSet())) : null);
    }

    public List<UserDto> convert(List<Users> fromList) {
        return fromList
                .stream()
                .map(this::convert).collect(Collectors.toList());
    }

}
