package ru.exception;
//todo пакет должен находиться внутри liga.crud. Там основной проект. А таким образом получается что он под доменом "ru"
// + почему бы не сделать один общий бизнес Exception ?
public class IdNotFoundException extends Exception {
    public IdNotFoundException(String message) {
        super(message);
    }
}
