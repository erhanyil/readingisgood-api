package com.example.readingisgood.constant;

public enum GenericError {

    RECORD_NOT_FOUND(804),
    RECORD_ALREADY_EXIST(803),
    CREDENTIAL_WRONG(802),
    INACTIVE_USER(801),
    GENERIC_ERROR(800),
    INVALID_STOCK(101);

    private int errorCode;

    GenericError(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
