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

import java.util.ArrayList;
import java.util.List;
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
        assertThrows(IllegalArgumentException.class,
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
                () -> assertEquals("joaoragazzo", response.getUsername())
        );
    }

    @Test
    @DisplayName("findById > When the id is not null > When the user is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenTheUserIsFoundThrowAnException() {
        when(repository.findById(2)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> service.findById(2));
    }
    
    @Test
    @DisplayName("getAllUsers > When there are no users > Return a empty list")
    void getAllUsersWhenThereAreNoUsersReturnAEmptyList() {
        when(repository.findAll()).thenReturn(new ArrayList<User>());

        List<User> users = service.getAllUsers();

        assertEquals(0, users.size());
    }

    @Test
    @DisplayName("getAllUsers > When there are users > Return the list containing all users")
    void getAllUsersWhenThereAreUsersReturnTheListContainingAllUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User(1, "User1", "username1", null, null, null, null));
        users.add(new User(2, "User2", "username2", null, null, null, null));
        users.add(new User(3, "User3", "username3", null, null, null, null));

        when(repository.findAll()).thenReturn(users);

        List<User> response = service.getAllUsers();

        assertAll(
                () -> assertEquals(3, response.size()),
                () -> assertEquals(1, response.get(0).getId()),
                () -> assertEquals("User1", response.get(0).getName()),
                () -> assertEquals("username1", response.get(0).getUsername()),
                () -> assertNull(response.get(0).getEmail()),
                () -> assertNull(response.get(0).getPhone()),
                () -> assertNull(response.get(0).getWebsite()),
                () -> assertNull(response.get(0).getPosts()),
                () -> assertEquals(2, response.get(1).getId()),
                () -> assertEquals("User2", response.get(1).getName()),
                () -> assertEquals("username2", response.get(1).getUsername()),
                () -> assertNull(response.get(1).getEmail()),
                () -> assertNull(response.get(1).getPhone()),
                () -> assertNull(response.get(1).getWebsite()),
                () -> assertNull(response.get(1).getPosts()),
                () -> assertEquals(3, response.get(2).getId()),
                () -> assertEquals("User3", response.get(2).getName()),
                () -> assertEquals("username3", response.get(2).getUsername()),
                () -> assertNull(response.get(2).getEmail()),
                () -> assertNull(response.get(2).getPhone()),
                () -> assertNull(response.get(2).getWebsite()),
                () -> assertNull(response.get(2).getPosts())
        );
    }

}
