package com.ugurukku.secondhand.services;


import com.ugurukku.secondhand.TestSupport;
import com.ugurukku.secondhand.dto.CreateUserRequest;
import com.ugurukku.secondhand.dto.UpdateUserRequest;
import com.ugurukku.secondhand.dto.UserDto;
import com.ugurukku.secondhand.dto.UserDtoConverter;
import com.ugurukku.secondhand.exceptions.UserNotFoundException;
import com.ugurukku.secondhand.models.UserInformation;
import com.ugurukku.secondhand.repositories.UserRepository;
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
public class UserServiceTest extends TestSupport {

    @Mock
    private UserRepository repository;
    @Mock
    private UserDtoConverter converter;

    @InjectMocks
    private UserService service;


    @Test
    public void testGetAllUsers_itShouldReturnUserDtoList() {
        List<UserInformation> userList = generateUsers();
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

        UserInformation userInformation = generateUserInformation();
        UserDto userDto = generateUserDto(userInformation);

        when(repository.findUserByEmail("ugur@com")).thenReturn(Optional.of(userInformation));
        when(converter.convert(userInformation)).thenReturn(userDto);

        UserDto result = service.getByEmail("ugur@com");

        assertEquals(result, userDto);


        verify(repository).findUserByEmail("ugur@com");
        verify(converter).convert(userInformation);

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
        UserInformation newInformation = new UserInformation(1L, request.getEmail(), request.getFirstName(), request.getLastName(), request.getPostCode(), false);

        UserDto userDto = new UserDto(newInformation.getEmail(), newInformation.getFirstName(), newInformation.getLastName(), newInformation.getPostCode(), newInformation.getActive());


        when(repository.save(any(UserInformation.class))).thenReturn(newInformation);
        when(converter.convert(newInformation)).thenReturn(userDto);

        UserDto result = service.add(request);

        assertEquals(result, userDto);
        verify(converter).convert(newInformation);
        verify(repository).save(any(UserInformation.class
        ));

    }

    @Test
    public void testUpdateUser_whenUserMailExistsAndUserIsActive_itShouldReturnUpdatedUserDto() {

        String mail = "ugur@com";

        UpdateUserRequest request = new UpdateUserRequest("Ugur", "Kerimov");
        UserInformation information = new UserInformation(1L,mail, request.getFirstName(), request.getLastName(), "AZ1010", true);

        UserInformation savedInformation = new UserInformation(1L, mail, request.getFirstName(), request.getLastName(), "AZ1010", false);
        UserDto userDto = new UserDto(savedInformation.getEmail(), savedInformation.getFirstName(), savedInformation.getLastName(), savedInformation.getPostCode(), savedInformation.getActive());

        when(repository.findUserByEmail(mail)).thenReturn(Optional.of(information));
        when(repository.save(any(UserInformation.class))).thenReturn(savedInformation);
        when(converter.convert(savedInformation)).thenReturn(userDto);

        UserDto result = service.update(request,mail);

        assertEquals(result, userDto);
        verify(converter).convert(savedInformation);
        verify(repository).save(any(UserInformation.class
        ));

    }




}