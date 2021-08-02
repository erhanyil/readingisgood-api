package com.example.readingisgood.dto.response;

public class SuccessResponseDto extends ResponseDto {

    public SuccessResponseDto() {
        this.setCode(200);
        this.setMsg("SUCCESS");
    }
}
