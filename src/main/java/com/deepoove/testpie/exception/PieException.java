package com.deepoove.testpie.exception;

public class PieException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PieException(String message, Throwable t) {
        super(message, t);
    }

    public PieException(String message) {
        super(message);
    }

}
