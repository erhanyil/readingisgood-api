package com.example.readingisgood.handler;


import com.example.readingisgood.constant.GenericError;

public class FriendlyException extends RuntimeException {

    private Integer code;

    public FriendlyException(String s) {
        super(s);
    }

    public FriendlyException(Throwable throwable) {
        super(throwable);
    }

    public FriendlyException(GenericError recordNotFound) {
        super(recordNotFound.name());
        this.code = recordNotFound.getErrorCode();
    }

    public Integer getCode() {
        return this.code;
    }

}