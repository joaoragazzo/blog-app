package com.blog.unit;

import com.blog.entity.User;
import com.blog.exception.UserNotFoundException;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Test
    @DisplayName("findById > When the id is null > Throw an exception")
    void findByIdWhenTheIdIsNullThrowAnException() {
        assertThrows(IllegalAccessError.class,
                () -> service.findById(null)
        );
    }

    @Test
    @DisplayName("findById > When the id is not null > When the user is found > Return user")
    void findByIdWhenTheIdIsNotNullWhenTheUserIsFoundReturnUser() {
        when(repository.findById(1)).thenReturn(Optional.of(User.builder()
                .id(1)
                .name("Joao")
                .username("joaoragazzo")
                .build()));


        User response = service.findById(1);

        assertAll(
                () -> assertEquals(1, response.getId()),
                () -> assertEquals("Joao", response.getName()),
                () -> assertEquals("joaoragazzo", response.getName())
        );
    }

    @Test
    @DisplayName("findById > When the id is not null > When the user is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenTheUserIsFoundThrowAnException() {
        when(repository.findById(2)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> service.findById(2));
    }

}
