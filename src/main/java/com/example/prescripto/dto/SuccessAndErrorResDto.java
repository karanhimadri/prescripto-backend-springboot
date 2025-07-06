package com.example.prescripto.dto;

public class SuccessAndErrorResDto implements BaseResponseDto {
    private Boolean status;
    private String message;

    public SuccessAndErrorResDto(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
