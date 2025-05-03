package by.bnd.intensive_spring.common.util;

import by.bnd.intensive_spring.model.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ServerResponseHelper {
    public static <T> ResponseEntity<ServerResponse<T>> ok(T result, HttpStatus httpStatus) {
        ServerResponse<T> response = ServerResponse.<T>builder()
                .isSuccess(true)
                .errorMessages(new ArrayList<>())
                .result(result)
                .statusCode(httpStatus)
                .build();
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <T> ResponseEntity<ServerResponse<T>> ok(T result) {
        return ok(result, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ServerResponse<T>> notFound(T result, List<String> errors) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ServerResponse<T> response = ServerResponse.<T>builder()
                .result(result)
                .statusCode(httpStatus)
                .isSuccess(false)
                .errorMessages(errors)
                .build();
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <T> ResponseEntity<ServerResponse<T>> notFound(T result) {
        return notFound(result, Collections.singletonList("Данные не найдены"));
    }

}
