package ru.liga.crud.service;

import java.util.ResourceBundle;

public class ResourceBundleService {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("messagesForInvalidRequests");

    public String getStringMessageByKey(String key) { //todo название getMessage
        if (key == null) {
            return "Key is null"; //todo в константу
        } else {
            return resourceBundle.getString(key);
        }
    }
}
