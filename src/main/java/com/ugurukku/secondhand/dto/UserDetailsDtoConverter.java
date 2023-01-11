package com.ugurukku.secondhand.dto;

import com.ugurukku.secondhand.models.UserDetails;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper
public interface UserDetailsDtoConverter {

     UserDetailsDto convert(UserDetails from);

     List<UserDetailsDto> convert(List<UserDetails> from);
}
