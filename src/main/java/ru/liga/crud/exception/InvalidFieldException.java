package ru.liga.crud.exception;
//todo пакет должен находиться внутри liga.crud. Там основной проект. А таким образом получается что он под доменом "ru"
// done
// + почему бы не сделать один общий бизнес Exception ?
// done
public class InvalidFieldException extends Exception {
    public InvalidFieldException(String message) {
        super(message);
    }
}
