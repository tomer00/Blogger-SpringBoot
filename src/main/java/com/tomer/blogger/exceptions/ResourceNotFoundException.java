package com.tomer.blogger.exceptions;


public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException(String resourceName, String fieldName, String fieldVal) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldVal));
    }
}
