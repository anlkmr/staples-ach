package com.emagia.ach.exception;

public class AnotherCustomException
        extends RuntimeException {

    private String message;

    public AnotherCustomException() {}

    public AnotherCustomException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}