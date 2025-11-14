package com.mthree.exception;

public class ResourceAlreadyExistsException extends Exception{

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(Long id, String resourceName) {
        super(resourceName + " with ID " + id + " already exists");
    }
}
