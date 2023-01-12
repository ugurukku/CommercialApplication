package com.ugurukku.secondhand.user.services;



import com.ugurukku.secondhand.user.TestSupport;
import com.ugurukku.secondhand.user.dto.CreateUserRequest;
import com.ugurukku.secondhand.user.dto.UpdateUserRequest;
import com.ugurukku.secondhand.user.dto.UserDto;
import com.ugurukku.secondhand.user.dto.UserDtoConverter;
import com.ugurukku.secondhand.user.exceptions.UserNotActiveException;
import com.ugurukku.secondhand.user.exceptions.UserNotFoundException;
import com.ugurukku.secondhand.user.models.Users;
import com.ugurukku.secondhand.user.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest extends TestSupport {

    @Mock
    private UsersRepository repository;
    @Mock
    private UserDtoConverter converter;

    @InjectMocks
    private UsersService service;


    @Test
    public void testGetAllUsers_itShouldReturnUserDtoList() {
        List<Users> userList = generateUsers();
        List<UserDto> userDtoList = generateUserDtoList(userList);

        when(repository.findAll()).thenReturn(userList);
        when(converter.convert(userList)).thenReturn(generateUserDtoList(userList));


        List<UserDto> result = service.getAll();

        assertEquals(result, userDtoList);
        verify(repository).findAll();
        verify(converter).convert(userList);
    }

    @Test
    public void testGetByEmail_whenUserMailExists_itShouldReturnUserDto() {

        Users users = generateUserInformation();
        UserDto userDto = generateUserDto(users);

        when(repository.findUserByEmail("ugur@com")).thenReturn(Optional.of(users));
        when(converter.convert(users)).thenReturn(userDto);

        UserDto result = service.getByEmail("ugur@com");

        assertEquals(result, userDto);


        verify(repository).findUserByEmail("ugur@com");
        verify(converter).convert(users);

    }

    @Test
    public void testGetByEmail_whenUserMailDoesNotExists_itShouldThrowUserNotFoundException() {
        String mail = "mail@folksdev.net";

        when(repository.findUserByEmail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                service.getByEmail(mail));

        verify(repository).findUserByEmail(mail);
        verifyNoInteractions(converter);
    }

    @Test
    public void testCreateUser_itShouldReturnCreatedUserDto() {

        CreateUserRequest request = new CreateUserRequest("ugur@com", "Ugur", "Kerimov", "AZ1090");
        Users newInformation = new Users(1L, request.getEmail(), request.getFirstName(), request.getLastName(), request.getPostCode(), false);

        UserDto userDto = new UserDto(newInformation.getEmail(), newInformation.getFirstName(), newInformation.getLastName(), newInformation.getPostCode(), newInformation.getActive(),null);


        when(repository.save(any(Users.class))).thenReturn(newInformation);
        when(converter.convert(newInformation)).thenReturn(userDto);

        UserDto result = service.createUser(request);

        assertEquals(result, userDto);
        verify(converter).convert(newInformation);
        verify(repository).save(any(Users.class
        ));

    }

    @Test
    public void testUpdateUser_whenUserMailExistsAndUserIsActive_itShouldReturnUpdatedUserDto() {

        String mail = "ugur@com";

        UpdateUserRequest request = new UpdateUserRequest("Ugur", "Kerimov");
        Users information = new Users(1L, mail, request.getFirstName(), request.getLastName(), "AZ1010", true);

        Users savedInformation = new Users(1L, mail, request.getFirstName(), request.getLastName(), "AZ1010", false);
        UserDto userDto = new UserDto(savedInformation.getEmail(), savedInformation.getFirstName(), savedInformation.getLastName(), savedInformation.getPostCode(), savedInformation.getActive(),null);

        when(repository.findUserByEmail(mail)).thenReturn(Optional.of(information));
        when(repository.save(any(Users.class))).thenReturn(savedInformation);
        when(converter.convert(savedInformation)).thenReturn(userDto);

        UserDto result = service.update(request, mail);

        assertEquals(result, userDto);
        verify(converter).convert(savedInformation);
        verify(repository).save(any(Users.class
        ));

    }

    @Test
    public void testUpdateUser_whenUserMailDoesNotExist_itShouldReturnUpdatedUserDto() {

        String mail = "ugur@com";

        UpdateUserRequest request = new UpdateUserRequest("Ugur", "Kerimov");

        when(repository.findUserByEmail(mail)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                service.update(request, mail));

        verify(repository).findUserByEmail(mail);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(converter);
    }

    @Test
    public void testUpdateUser_whenUserMailExistsButUserIsNotActive_itShouldReturnUpdatedUserDto() {

        String mail = "ugur@com";

        UpdateUserRequest request = new UpdateUserRequest("Ugur", "Kerimov");
        Users information = new Users(1L, mail, request.getFirstName(), request.getLastName(), "AZ1010", false);

        when(repository.findUserByEmail(mail)).thenReturn(Optional.of(information));

        assertThrows(UserNotActiveException.class, () ->
                service.update(request, mail)
        );

        verify(repository).findUserByEmail(mail);
        verifyNoMoreInteractions(repository);
        verifyNoInteractions(converter);
    }

    @Test
    public void testDeactivateUser_whenUserIdExists_itShouldUpdateUserByActiveFalse() {

        String mail = "ugur@com";


        Users information = new Users(
                TestSupport.userId, mail, "firstName", "lastName", "AZ1010", true);

        Users savedInformation = new Users(
                TestSupport.userId, mail, "firstName", "lastName", "AZ1010", false);

        when(repository.findById(TestSupport.userId)).thenReturn(Optional.of(information));

        service.deactivate(TestSupport.userId);

        verify(repository).findById(TestSupport.userId);
        verify(repository).save(savedInformation);
    }

    @Test
    public void testDeactivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {

        when(repository.findById(TestSupport.userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                service.deactivate(TestSupport.userId)
        );


        verify(repository).findById(TestSupport.userId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testActivateUser_whenUserIdExists_itShouldUpdateUserByActiveTrue() {

        String mail = "ugur@com";


        Users information = new Users(
                TestSupport.userId, mail, "firstName", "lastName", "AZ1010", false);

        Users savedInformation = new Users(
                TestSupport.userId, mail, "firstName", "lastName", "AZ1010", true);

        when(repository.findById(TestSupport.userId)).thenReturn(Optional.of(information));

        service.activate(TestSupport.userId);

        verify(repository).findById(TestSupport.userId);
        verify(repository).save(savedInformation);
    }

    @Test
    public void testActivateUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {

        when(repository.findById(TestSupport.userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                service.activate(TestSupport.userId)
        );


        verify(repository).findById(TestSupport.userId);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testDeleteUser_whenUserIdExists_itShouldDeleteUser() {

        String mail = "ugur@com";

        Users information = new Users(
                TestSupport.userId, mail, "firstName", "lastName", "AZ1010", false);
        when(repository.findById(TestSupport.userId)).thenReturn(Optional.of(information));

        service.delete(TestSupport.userId);


        verify(repository).findById(TestSupport.userId);
        verify(repository).deleteById(TestSupport.userId);
    }

    @Test
    public void testDeleteUser_whenUserIdDoesNotExist_itShouldThrowUserNotFoundException() {


        when(repository.findById(TestSupport.userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                service.delete(TestSupport.userId)
        );

        verify(repository).findById(TestSupport.userId);
        verifyNoMoreInteractions(repository);
    }
}