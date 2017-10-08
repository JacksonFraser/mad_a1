package com.example.s3529589.mad_a1.Exceptions;

public class InvalidMeetingInput extends Exception {
    private String message;

    public InvalidMeetingInput(String message) {
        super(message);
    }

}
