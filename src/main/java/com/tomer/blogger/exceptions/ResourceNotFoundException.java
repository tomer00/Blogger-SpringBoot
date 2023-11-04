package com.tomer.blogger.exceptions;


public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
    String fieldName;
    int fieldVal;

    public ResourceNotFoundException(String resourceName, String fieldName, int fieldVal) {
        super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldVal));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldVal = fieldVal;
    }
}
