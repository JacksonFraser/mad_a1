package com.example.s3529589.mad_a1.Exceptions;

/**
 * Created by s3529589 on 8/26/17.
 */

public class InvalidDateInput extends Exception {
    private String message;

    public InvalidDateInput(String message){
        super(message);
    }

}
