package ru.liga.crud.exception;


//todo по сути у тебя два Exception имеют одну бизнес роль ошибка валидации
// сделай один Exception и исполбзуй его. Можно назвать ValidationException
public class InvalidFieldException extends Exception {
    public InvalidFieldException(String message) {
        super(message);
    }
}
