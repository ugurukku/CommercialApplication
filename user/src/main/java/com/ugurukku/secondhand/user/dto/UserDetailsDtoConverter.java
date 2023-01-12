package com.ugurukku.secondhand.user.dto;

import com.ugurukku.secondhand.user.models.UserDetails;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "userDetailsDtoConverter")
public interface UserDetailsDtoConverter {

     UserDetailsDto convert(UserDetails from);

     List<UserDetailsDto> convert(List<UserDetails> from);
}
