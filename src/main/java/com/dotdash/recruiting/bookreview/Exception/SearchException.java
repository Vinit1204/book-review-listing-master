package com.dotdash.recruiting.bookreview.Exception;


public class SearchException extends RuntimeException{

    private String message;

    public SearchException(String message){
        super(message);
        this.message = message;
    }
}
