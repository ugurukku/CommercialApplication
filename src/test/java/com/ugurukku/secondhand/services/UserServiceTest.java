package com.ugurukku.secondhand.services;


import com.ugurukku.secondhand.TestSupport;
import com.ugurukku.secondhand.dto.UserDtoConverter;
import com.ugurukku.secondhand.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserServiceTest {

    private UserRepository repository;

    private UserDtoConverter converter;

    private UserService service;

    @BeforeAll
    public void setUp() {
        repository = Mockito.mock(UserRepository.class);
        converter = Mockito.mock(UserDtoConverter.class);

        service = new UserService(repository, converter);
    }

    @Test
    public void testGetAllUsers_itShouldReturnUserDtoList() {
        Mockito.when(repository.findAll()).thenReturn(TestSupport.generateUsers());
    }

}