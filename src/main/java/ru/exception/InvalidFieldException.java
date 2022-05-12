package ru.exception;
//todo пакет должен находиться внутри liga.crud. Там основной проект. А таким образом получается что он под доменом "ru"
public class InvalidFieldException extends Exception {
    public InvalidFieldException(String message) {
        super(message);
    }
}
