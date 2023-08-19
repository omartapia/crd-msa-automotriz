package com.pichincha.crd.automotriz.exceptions;

public class ApplicationException extends RuntimeException {
    public ApplicationException(Exception exception) {
        super(exception);
    }

    public ApplicationException(String errorMessage) {
        super(errorMessage);
    }
}
