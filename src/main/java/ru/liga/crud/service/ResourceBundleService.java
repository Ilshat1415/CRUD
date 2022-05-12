package ru.liga.crud.service;

import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

public class ResourceBundleService {
    //Todo название файла "text" не очень. Лучше назвать по тому что содержит
    // done
    // + он используется в нескольких местах лучше вынести в отдельный класс по работе с Bundle
    // done
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("messagesForInvalidRequests");
    //todo лучше писать полное название переменной
    // done

    public String getStringMessageByKey(String key) {
        if (key == null) {
            return "Key is null";
        } else {
            return resourceBundle.getString(key);
        }
    }
}
