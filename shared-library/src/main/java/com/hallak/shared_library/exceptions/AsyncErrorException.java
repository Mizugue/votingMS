package com.hallak.shared_library.exceptions;

public class AsyncErrorException extends RuntimeException{


    private AsyncError asyncError;

    public AsyncErrorException() {
    }

    public AsyncErrorException(AsyncError asyncError){
        this.asyncError = asyncError;
    }

    public AsyncErrorException(String message) {
        super(message);
    }



    public AsyncError getAsyncError() {
        return asyncError;
    }
}
