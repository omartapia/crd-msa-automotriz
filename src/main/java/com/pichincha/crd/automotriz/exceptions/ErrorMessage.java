package com.pichincha.crd.automotriz.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class ErrorMessage {
    private Date timestamp;
    private String message;

    public ErrorMessage(String message) {
        this.timestamp = new Date();
        this.message = message;
    }
}