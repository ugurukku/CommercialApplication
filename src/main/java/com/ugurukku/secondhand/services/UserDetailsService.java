package com.ugurukku.secondhand.services;

import com.ugurukku.secondhand.dto.*;
import com.ugurukku.secondhand.exceptions.UserDetailsNotFoundException;
import com.ugurukku.secondhand.models.UserDetails;
import com.ugurukku.secondhand.models.Users;
import com.ugurukku.secondhand.repositories.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    private final UserDetailsRepository detailsRepository;
    private final UsersService usersService;
    private final UserDetailsDtoConverter detailsDtoConverter;


    public UserDetailsService(UserDetailsRepository userDetailsRepository, UsersService usersService, UserDetailsDtoConverter userDetailsDtoConverter) {
        this.detailsRepository = userDetailsRepository;
        this.usersService = usersService;
        this.detailsDtoConverter = userDetailsDtoConverter;
    }

    public UserDetailsDto createUserDetails(final CreateUserDetailsRequest request) {

        Users user = usersService.findUserById(request.getUserId());

        UserDetails userDetails = new UserDetails(request.getPhoneNumber(), request.getAddress(),
                request.getCity(), request.getCountry(), user);

        return detailsDtoConverter.convert(detailsRepository.save(userDetails));
    }

    public UserDetailsDto updateUserDetails(final Long userDetailsId, final UpdateUserDetailsRequest request) {

        UserDetails userDetails = findUserDetailsById(userDetailsId);

        UserDetails updateUserDetails = new UserDetails(
                userDetails.getId(),
                request.getPhoneNumber(),
                request.getAddress(),
                request.getCity(),
                request.getCountry(),
                userDetails.getUsers()
        );

        return detailsDtoConverter.convert(detailsRepository.save(updateUserDetails));
    }

    public void deleteUserDetails(final Long id){
        findUserDetailsById(id);
        detailsRepository.deleteById(id);
    }

    private UserDetails findUserDetailsById(final Long userDetailsId) {
        return detailsRepository.findById(userDetailsId)
                .orElseThrow(() -> new UserDetailsNotFoundException(
                        String.format("User details not found for id=%s", userDetailsId)));
    }

}
