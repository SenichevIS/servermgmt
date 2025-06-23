package com.servermgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("Entity not found");
    }

    public EntityNotFoundException(String entityName) {
        super(entityName + " not found");
    }

    public EntityNotFoundException(String entityName, Long id) {
        super(entityName + " with ID " + id + " not found");
    }

    public EntityNotFoundException(String entityName, String identifier) {
        super(entityName + " with identifier '" + identifier + "' not found");
    }
}