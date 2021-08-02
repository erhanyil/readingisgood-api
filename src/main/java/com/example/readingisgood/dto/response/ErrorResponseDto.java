package com.example.readingisgood.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorResponseDto extends ResponseDto {

    @JsonIgnore
    private String errorType;

    public ErrorResponseDto(Throwable data) {
        this.errorType = data.getClass().getSimpleName();
        try {
            this.setMsg(data.getCause().getMessage());
        } catch (Exception e) {
            this.setMsg(data.getMessage());
        }
        this.setCode(800);
        this.setSuccess(false);
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
}
