package com.ugurukku.secondhand.services;


import com.ugurukku.secondhand.TestSupport;
import com.ugurukku.secondhand.dto.UserDto;
import com.ugurukku.secondhand.dto.UserDtoConverter;
import com.ugurukku.secondhand.models.UserInformation;
import com.ugurukku.secondhand.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

public class UserServiceTest extends TestSupport {

    @Mock
    private UserRepository repository;

    @Mock
    private UserDtoConverter converter;

    @InjectMocks
    private UserService service;

//    @BeforeEach
//    public void setUp() {
//        repository = Mockito.mock(UserRepository.class);
//        converter = Mockito.mock(UserDtoConverter.class);
//
//        service = new UserService(repository, converter);
//    }

    @Test
    public void testGetAllUsers_itShouldReturnUserDtoList() {

        List<UserInformation> userList = generateUsers();
        List<UserDto> userDtoList = generateUserDtoList(userList);

        Mockito.when(repository.findAll()).thenReturn(userList);
        Mockito.when(converter.convert(userList)).thenReturn(generateUserDtoList(userList));

        List<UserDto> result = service.getAll();

        Assertions.assertEquals(userDtoList, result);
        Mockito.verify(repository).findAll();
        Mockito.verify(converter).convert(userList);
    }

}