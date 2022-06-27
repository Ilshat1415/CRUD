package ru.liga.crud.testdata;

import ru.liga.crud.entity.User;

public class UserTestData {

    public static User getValidUser() {
        User validUser = new User();
        validUser.setUserName("admin");
        validUser.setPassword("admin");

        return validUser;
    }

    public static User getInvalidUser() {
        User invalidUser = new User();
        invalidUser.setUserName("test");
        invalidUser.setPassword("test");

        return invalidUser;
    }
}
