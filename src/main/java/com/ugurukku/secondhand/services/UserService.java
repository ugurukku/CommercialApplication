package com.ugurukku.secondhand.services;

import com.ugurukku.secondhand.dto.CreateUserRequest;
import com.ugurukku.secondhand.models.User;
import com.ugurukku.secondhand.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    public User add(CreateUserRequest createUserRequest) {
        return null;
    }

    public User update(UpdateUserRequest createUserRequest, Long id) {
        return null;
    }

    public User deactivate(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return user;
    }

    public User delete(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return user;
    }

}

