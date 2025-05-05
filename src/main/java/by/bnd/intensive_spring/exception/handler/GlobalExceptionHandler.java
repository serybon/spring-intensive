package by.bnd.intensive_spring.exception.handler;

import by.bnd.intensive_spring.common.util.ServerResponseHelper;
import by.bnd.intensive_spring.exception.customException.EntityNotFoundException;
import by.bnd.intensive_spring.exception.customException.ValidationException;
import by.bnd.intensive_spring.model.ServerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServerResponse<Void>> handleValidationException(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                .toList();
        return ServerResponseHelper.conflict(null, errors);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ServerResponse<Void>> handleEntityNotFound(
            EntityNotFoundException ex) {
        return ServerResponseHelper.notFound(null,
                Collections.singletonList(ex.getMessage()));
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ServerResponse<Void>> handleValidationError(
            ValidationException ex) {
        return ServerResponseHelper.conflict(null,
                Collections.singletonList(ex.getMessage()));

    }
}
