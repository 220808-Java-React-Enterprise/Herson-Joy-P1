package com.revature.P1.utils.custom_exceptions;

public class RegistrationPendingException extends RuntimeException {
    public RegistrationPendingException() {
    }

    public RegistrationPendingException(String message) {
        super(message);
    }

    public RegistrationPendingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationPendingException(Throwable cause) {
        super(cause);
    }

    public RegistrationPendingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
