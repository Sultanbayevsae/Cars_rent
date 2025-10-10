package org.example.server.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFountException.class)
    public ResponseEntity<ErrorDTO> error_404(DataNotFountException e, HttpServletRequest req){
        return ResponseEntity
                .status(404)
                .body(ErrorDTO.builder()
                        .errorPath(req.getRequestURI())
                        .errorBody(e.getMessage())
                        .errorCode(404)
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDTO> methodArgumentTypeException_400(MethodArgumentTypeMismatchException e, HttpServletRequest req){
        return ResponseEntity
                .status(400)
                .body(ErrorDTO.builder()
                        .errorPath(req.getRequestURI())
                        .errorBody(e.getMessage())
                        .errorCode(400)
                        .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> methodArgumentNotValidException_400(MethodArgumentNotValidException e, HttpServletRequest req){
        Map<String, List<String>> errorBody = new HashMap<>();
        for (FieldError fieldError : e.getFieldErrors()) {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();

            errorBody.get(field);
            // key=value
            errorBody.compute(field, (k_field, v_messages) ->{
                v_messages = Objects.requireNonNullElse(v_messages, new ArrayList<>());
                v_messages.add(message);
                return v_messages;
            });
        }

        if (errorBody.isEmpty()) {
            String message = e.getBindingResult()
                    .getAllErrors()
                    .stream()
                    .findFirst()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .orElse(e.getMessage());
            errorBody.put("ERROR", List.of(message));
        }
        return ResponseEntity
                .status(400)
                .body(ErrorDTO.builder()
                        .errorPath(req.getRequestURI())
                        .errorBody(errorBody)
                        .errorCode(400)
                        .build()
                );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> exception_500(Exception e, HttpServletRequest req){
        return ResponseEntity
                .status(500)
                .body(ErrorDTO.builder()
                        .errorPath(req.getRequestURI())
                        .errorBody(e.getMessage())
                        .errorCode(500)
                        .build()
                );
    }

}
