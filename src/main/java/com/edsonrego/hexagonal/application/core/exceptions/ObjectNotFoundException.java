package com.edsonrego.hexagonal.application.core.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(final String id){
        super("Object with id "+ " not found");
    }
}
