package com.hallak.shared_library.exceptions;

import com.hallak.shared_library.errorhandling.APIError;

public class EntityAlreadyExistsException extends RuntimeException{

    private APIError apiError;

    public EntityAlreadyExistsException() {
    }

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(APIError apiError){
        this.apiError = apiError;
    }

    public APIError getApiError() {
        return apiError;
    }
}