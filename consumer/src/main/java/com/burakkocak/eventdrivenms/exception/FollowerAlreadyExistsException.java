package com.burakkocak.eventdrivenms.exception;

import java.util.UUID;

public class FollowerAlreadyExistsException extends Exception {
    private static final String message = "follower_list_already_exists";
    public FollowerAlreadyExistsException(UUID element) {
        super(message + ": '" + element + "'");
    }
    public FollowerAlreadyExistsException(Exception ex) {
        super(message + ": '" + ex + "'");
    }
    public FollowerAlreadyExistsException() {
        super(message);
    }
}
