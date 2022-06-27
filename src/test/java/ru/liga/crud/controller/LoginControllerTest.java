package ru.liga.crud.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import ru.liga.crud.initializer.CrudInitializer;
import ru.liga.crud.entity.User;
import ru.liga.crud.response.JwtResponse;

import static org.assertj.core.api.Assertions.assertThat;

class LoginControllerTest extends CrudInitializer {
    private final LoginController loginController;

    @Autowired()
    public LoginControllerTest(LoginController loginController) {
        this.loginController = loginController;
    }

    @Test
    void authUser() {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin");

        ResponseEntity<JwtResponse> responseEntity = loginController.authUser(user);

        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody().getAuthType()).isEqualTo("Bearer");
        assertThat(responseEntity.getBody().getJwtToken()).isNotNull();
        assertThat(responseEntity.getBody().getJwtToken().isEmpty()).isFalse();
    }
}