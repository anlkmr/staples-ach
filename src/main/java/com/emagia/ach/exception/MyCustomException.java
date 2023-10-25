package com.emagia.ach.exception;


public class MyCustomException
        extends RuntimeException {

    private String message;

    public MyCustomException() {}

    public MyCustomException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
