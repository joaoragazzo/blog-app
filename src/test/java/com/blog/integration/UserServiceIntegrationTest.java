package com.blog.integration;

import com.blog.entity.User;
import com.blog.exception.UserNotFoundException;
import com.blog.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class UserServiceIntegrationTest
{

    @Autowired
    private UserService service;

    @Test
    @DisplayName("findById > When the id is null > Throw an exception")
    void findByIdWhenTheIdIsNullThrowAnException() {
        Assertions.assertThrows(IllegalAccessError.class,
                () -> service.findById(null));
    }

    @Test
    @DisplayName("findById > When the id is not null > When a user is found > Return the user")
    void findByIdWhenTheIdIsNotNullWhenAUserIsFoundReturnTheUser() {
        User user = service.findById(6);

        Assertions.assertAll(
                () -> Assertions.assertEquals(6, user.getId()),
                () -> Assertions.assertEquals("Mrs. Dennis Schulist", user.getName()),
                () -> Assertions.assertEquals("Leopoldo_Corkery", user.getUsername()),
                () -> Assertions.assertEquals("Karley_Dach@jasper.info", user.getEmail()),
                () -> Assertions.assertEquals("1-477-935-8478 x6430", user.getPhone()),
                () -> Assertions.assertEquals("ola.org", user.getWebsite())
        );
    }

    @Test
    @DisplayName("findById > When the id is not null > When no user is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenNoUserIsFoundThrowAnException() {
       Assertions.assertThrows(UserNotFoundException.class,
               () -> service.findById(11));
    }

}
