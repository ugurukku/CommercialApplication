package com.ugurukku.secondhand.services;


import com.ugurukku.secondhand.TestSupport;
import com.ugurukku.secondhand.dto.UserDto;
import com.ugurukku.secondhand.dto.UserDtoConverter;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

}