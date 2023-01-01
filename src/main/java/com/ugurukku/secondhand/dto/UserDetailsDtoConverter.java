package com.ugurukku.secondhand.dto;

import com.ugurukku.secondhand.models.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDetailsDtoConverter {

    public UserDetailsDto convert(UserDetails from) {
        return new UserDetailsDto(from.getPhoneNumber(), from.getAddress(), from.getCity(), from.getCountry());
    }

    public List<UserDetailsDto> convert(List<UserDetails> from) {
        return from.stream().map(this::convert).collect(Collectors.toList());
    }

}
