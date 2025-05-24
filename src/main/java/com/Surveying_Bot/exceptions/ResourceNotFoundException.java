package com.Surveying_Bot.exceptions;

import java.io.Serial;
import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID= 1L;

    String resourceName;
    String field;
    String fieldName;
    Long fieldId;
    UUID fieldUUID;

    public ResourceNotFoundException(String resourceName, String field, String fieldName){
        super(String.format("%s is not found with %s: %s", resourceName, field, fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String resourceName, String field, Long fieldId){
        super(String.format("%s is not found with %s: %s", resourceName, field, fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }

    public ResourceNotFoundException(String resourceName, String field, UUID fieldUUID) {
        super(String.format("%s is not found with %s: %s", resourceName, field, fieldUUID));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldUUID = fieldUUID;
    }

}
