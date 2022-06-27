package ru.liga.crud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import ru.liga.crud.initializer.InitializerForTests;
import ru.liga.crud.entity.User;
import ru.liga.crud.response.JwtResponse;

import static org.assertj.core.api.Assertions.assertThat;

class LoginControllerTest extends InitializerForTests {
    private final LoginController loginController;

    @Autowired()
    public LoginControllerTest(LoginController loginController) {
        this.loginController = loginController;
    }

    @Test
    void authUser_ValidUser_ValidToken() {
        User validUser = new User();
        validUser.setUserName("admin");
        validUser.setPassword("admin");

        ResponseEntity<JwtResponse> responseEntity = loginController.authUser(validUser);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody().getAuthType()).isEqualTo("Bearer");
        assertThat(responseEntity.getBody().getJwtToken()).isNotNull();
        assertThat(responseEntity.getBody().getJwtToken().isEmpty()).isFalse();
        assertThat(responseEntity.getBody().getMessage()).isNull();
    }

    @Test
    void authUser_InvalidUser_NullToken() {
        User invalidUser = new User();
        invalidUser.setUserName("test");
        invalidUser.setPassword("test");

        ResponseEntity<JwtResponse> responseEntity = loginController.authUser(invalidUser);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody().getAuthType()).isNull();
        assertThat(responseEntity.getBody().getJwtToken()).isNull();
        assertThat(responseEntity.getBody().getMessage()).isNotNull();
        assertThat(responseEntity.getBody().getMessage().isEmpty()).isFalse();
    }
}