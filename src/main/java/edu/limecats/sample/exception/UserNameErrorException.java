package edu.limecats.sample.exception;

public class UserNameErrorException extends RuntimeException{
    public UserNameErrorException(String message) {
        super(message);
    }
}
