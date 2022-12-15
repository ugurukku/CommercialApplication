package com.ugurukku.secondhand.services;

import com.ugurukku.secondhand.dto.CreateUserRequest;
import com.ugurukku.secondhand.dto.UpdateUserRequest;
import com.ugurukku.secondhand.dto.UserDto;
import com.ugurukku.secondhand.dto.UserDtoConverter;
import com.ugurukku.secondhand.exceptions.UserNotFoundException;
import com.ugurukku.secondhand.models.UserInformation;
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
        UserInformation userInformation = new UserInformation(
                createUserRequest.getEmail(),
                createUserRequest.getFirstName(),
                createUserRequest.getLastName(),
                createUserRequest.getPostCode(),
                true
        );
        return userDtoConverter.convert(userRepository.save(userInformation));
    }

    public UserDto update(UpdateUserRequest updateUserRequest, Long id) {

        UserInformation user = findUserById(id);

        String newFirstName = updateUserRequest.getFirstName() == null ? user.getFirstName() : updateUserRequest.getFirstName();

        String newLastName = updateUserRequest.getLastName() == null ? user.getLastName() : updateUserRequest.getLastName();

        UserInformation updatedUserInformation =
                new UserInformation(
                        user.getId(),
                        user.getEmail(),
                        newFirstName,
                        newLastName,
                        user.getPostCode(),
                        user.getActive());

        return userDtoConverter.convert(userRepository.save(updatedUserInformation));
    }

    public UserDto deactivate(Long id) {
        UserInformation user = findUserById(id);
        userRepository.deleteById(id);
        return userDtoConverter.convert(user);
    }

    public UserDto delete(Long id) {
        UserInformation user = findUserById(id);
        userRepository.deleteById(id);
        return userDtoConverter.convert(user);
    }

    public UserDto getByEmail(String email) {
        return userDtoConverter.convert(userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("Not found")));
    }

    private UserInformation findUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

}

