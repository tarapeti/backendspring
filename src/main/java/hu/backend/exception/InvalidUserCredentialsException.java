package hu.backend.exception;

public class InvalidUserCredentialsException extends RuntimeException {

    public InvalidUserCredentialsException(String message) {
        super(message);
    }
}
