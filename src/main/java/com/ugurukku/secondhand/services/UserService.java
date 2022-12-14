package com.ugurukku.secondhand.services;

import com.ugurukku.secondhand.dto.CreateUserRequest;
import com.ugurukku.secondhand.dto.UpdateUserRequest;
import com.ugurukku.secondhand.dto.UserDto;
import com.ugurukku.secondhand.dto.UserDtoConverter;
import com.ugurukku.secondhand.exceptions.UserNotFoundException;
import com.ugurukku.secondhand.models.User;
import com.ugurukku.secondhand.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userDtoConverter::convert).collect(Collectors.toList());
    }

    public UserDto getById(Long id) {
        return userDtoConverter.convert(findUserById(id));
    }

    public UserDto add(CreateUserRequest createUserRequest) {
        User user = new User(
                null,
                createUserRequest.getEmail(),
                createUserRequest.getFirstName(),
                createUserRequest.getLastname(),
                createUserRequest.getPostCode()
        );
        return userDtoConverter.convert(userRepository.save(user));
    }

    public UserDto update(UpdateUserRequest updateUserRequest, Long id) {
        User user = findUserById(id);
        User updatedUser =
                new User(
                        user.getId(),
                        user.getEmail(),
                        updateUserRequest.getFirstName(),
                        updateUserRequest.getLastname(),
                        user.getPostCode());

        return userDtoConverter.convert(userRepository.save(updatedUser));
    }

    public UserDto deactivate(Long id) {
        User user = findUserById(id);
        userRepository.deleteById(id);
        return userDtoConverter.convert(user);
    }

    public UserDto delete(Long id) {
        User user = findUserById(id);
        userRepository.deleteById(id);
        return userDtoConverter.convert(user);
    }

    private User findUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

}

