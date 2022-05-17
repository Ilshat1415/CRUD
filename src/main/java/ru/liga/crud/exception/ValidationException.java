package ru.liga.crud.exception;

public class ValidationException extends Exception {

    public ValidationException() {
        super("Validation error");
    }

    public ValidationException(String message) {
        super("Validation error. " + message);
    }
}
