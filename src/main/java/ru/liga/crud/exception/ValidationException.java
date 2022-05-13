package ru.liga.crud.exception;


//todo по сути у тебя два Exception имеют одну бизнес роль ошибка валидации
// сделай один Exception и исполбзуй его. Можно назвать ValidationException
// done
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
