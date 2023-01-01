package com.ugurukku.secondhand.services;

import com.ugurukku.secondhand.dto.CreateUserRequest;
import com.ugurukku.secondhand.dto.UpdateUserRequest;
import com.ugurukku.secondhand.dto.UserDto;
import com.ugurukku.secondhand.dto.UserDtoConverter;
import com.ugurukku.secondhand.exceptions.UserNotActiveException;
import com.ugurukku.secondhand.exceptions.UserNotFoundException;
import com.ugurukku.secondhand.models.Users;
import com.ugurukku.secondhand.repositories.UsersRepository;
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

    public UserDto add(CreateUserRequest createUserRequest) {
        Users users = new Users(
                createUserRequest.getEmail(),
                createUserRequest.getFirstName(),
                createUserRequest.getLastName(),
                createUserRequest.getPostCode(),
                true
        );
        return userDtoConverter.convert(usersRepository.save(users));
    }

    public UserDto update(UpdateUserRequest updateUserRequest, String email) {

        Users user = findUserByEmail(email);

        if (!user.getActive()) {
            throw new UserNotActiveException(String.format("User is not active, email: %s", email));
        }

        String newFirstName = updateUserRequest.getFirstName() == null ? user.getFirstName() : updateUserRequest.getFirstName();

        String newLastName = updateUserRequest.getLastName() == null ? user.getLastName() : updateUserRequest.getLastName();

        Users updatedUsers =
                new Users(
                        user.getId(),
                        user.getEmail(),
                        newFirstName,
                        newLastName,
                        user.getPostCode(),
                        user.getActive());

        return userDtoConverter.convert(usersRepository.save(updatedUsers));
    }

    public void activate(Long id) {
        changeActivityStatus(id, true);
    }

    public void deactivate(Long id) {
        changeActivityStatus(id, false);
    }

    public void delete(Long id) {

        findUserById(id);

        usersRepository.deleteById(id);

}

    private void changeActivityStatus(Long id, Boolean isActive) {
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

    private Users findUserByEmail(String email) {
        return usersRepository
                .findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found! , email : %s", email)));
    }

    private Users findUserById(Long id) {
        return usersRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found! , id : %s", id)));
    }

}
