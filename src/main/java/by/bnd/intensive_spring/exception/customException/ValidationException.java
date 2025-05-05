package by.bnd.intensive_spring.exception.customException;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
