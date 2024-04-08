package tech.ada.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomizaExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        var erros = exception.getBindingResult().getAllErrors().stream().map(error->error.getDefaultMessage()).toList();

        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<List<String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){

        var erros = List.of(exception.getMessage());

        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);

    }
}
