package com.blog.integration;

import com.blog.service.UserService;
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
        fail(" Not implemented");
    }

    @Test
    @DisplayName("findById > When the id is not null > When a user is found > Return the user")
    void findByIdWhenTheIdIsNotNullWhenAUserIsFoundReturnTheUser() {
        fail(" Not implemented");
    }

    @Test
    @DisplayName("findById > When the id is not null > When no user is found > Throw an exception")
    void findByIdWhenTheIdIsNotNullWhenNoUserIsFoundThrowAnException() {
        org.junit.jupiter.api.Assertions.fail(" Not implemented");
    }

}
