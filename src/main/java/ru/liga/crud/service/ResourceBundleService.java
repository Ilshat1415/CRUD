package ru.liga.crud.service;

import java.util.ResourceBundle;

public class ResourceBundleService {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("messagesForInvalidRequests");
    public static final String KEY_IS_NULL = "Key is null";

    public String getMessage(String key) {
        //todo название getMessage
        // done
        if (key == null) {
            return KEY_IS_NULL;
            //todo в константу
            // done
        } else {
            return resourceBundle.getString(key);
        }
    }
}
