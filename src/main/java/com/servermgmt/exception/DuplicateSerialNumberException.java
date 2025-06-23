package com.servermgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateSerialNumberException extends RuntimeException {
    public DuplicateSerialNumberException(String serialNumber) {
        super("Equipment with serial number '" + serialNumber + "' already exists");
    }

    public DuplicateSerialNumberException(String serialNumber, Throwable cause) {
        super("Equipment with serial number '" + serialNumber + "' already exists", cause);
    }
}