package com.ugurukku.secondhand.user.services;


import com.ugurukku.secondhand.user.dto.CreateUserRequest;
import com.ugurukku.secondhand.user.dto.UpdateUserRequest;
import com.ugurukku.secondhand.user.dto.UserDto;
import com.ugurukku.secondhand.user.dto.UserDtoConverter;
import com.ugurukku.secondhand.user.exceptions.UserNotActiveException;
import com.ugurukku.secondhand.user.exceptions.UserNotFoundException;
import com.ugurukku.secondhand.user.models.Users;
import com.ugurukku.secondhand.user.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final UserDtoConverter userDtoConverter;

    public UsersService(UsersRepository usersRepository, UserDtoConverter userDtoConverter) {
        this.usersRepository = usersRepository;
        this.userDtoConverter = userDtoConverter;
    }

    public List<UserDto> getAll() {
        return userDtoConverter.convert(usersRepository.findAll());

    }

    public UserDto getByEmail(String email) {
        return userDtoConverter.convert(findUserByEmail(email));
    }

    public UserDto createUser(final CreateUserRequest createUserRequest) {
        Users users = new Users(
                createUserRequest.getEmail(),
                createUserRequest.getFirstName(),
                createUserRequest.getLastName(),
                createUserRequest.getPostCode(),
                true
        );
        return userDtoConverter.convert(usersRepository.save(users));
    }

    public UserDto update(final UpdateUserRequest updateUserRequest, final String email) {

        Users user = findUserByEmail(email);

        if (!user.getActive()) {
            throw new UserNotActiveException(String.format("User is not active, email: %s", email));
        }

        Users updatedUsers =
                new Users(
                        user.getId(),
                        user.getEmail(),
                        updateUserRequest.getFirstName(),
                        updateUserRequest.getLastName(),
                        user.getPostCode(),
                        user.getActive());

        return userDtoConverter.convert(usersRepository.save(updatedUsers));
    }

    public void activate(final Long id) {
        changeActivityStatus(id, true);
    }

    public void deactivate(final Long id) {
        changeActivityStatus(id, false);
    }

    public void delete(Long id) {

        findUserById(id);

        usersRepository.deleteById(id);

    }

    private void changeActivityStatus(final Long id, final Boolean isActive) {
        Users user = findUserById(id);

        Users deactivatedUser = new Users(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPostCode(),
                isActive);
        usersRepository.save(deactivatedUser);
    }

    private Users findUserByEmail(final String email) {
        return usersRepository
                .findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found! , email : %s", email)));
    }

    protected Users findUserById(final Long id) {
        return usersRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found! , id : %s", id)));
    }

    public Boolean isUserIdExist(Long id) {
        return usersRepository.existsById(id);
    }
}

