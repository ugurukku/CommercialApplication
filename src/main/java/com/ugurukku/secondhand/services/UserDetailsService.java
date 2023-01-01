package com.ugurukku.secondhand.services;

import com.ugurukku.secondhand.repositories.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;


    public UserDetailsService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }


}
