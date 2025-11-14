package com.mthree.exception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Long id, String resourceName) {
        super(resourceName + " with ID " + id + " not found");
    }
}


