package com.burakkocak.eventdrivenms.exception;

import java.util.UUID;

public class UserAlreadyExistsException extends Exception {
    private static final String message = "user_already_exists";
    public UserAlreadyExistsException(UUID element) {
        super(message + ": '" + element + "'");
    }
    public UserAlreadyExistsException(Exception ex) {
        super(message + ": '" + ex + "'");
    }
    public UserAlreadyExistsException() {
        super(message);
    }
}
