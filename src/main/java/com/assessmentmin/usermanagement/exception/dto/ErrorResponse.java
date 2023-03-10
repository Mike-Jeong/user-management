package com.assessmentmin.usermanagement.exception.dto;

import com.assessmentmin.usermanagement.exception.type.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private ErrorCode errorCode;
    private String errorMessage;

    @Builder
    public ErrorResponse(int status, ErrorCode errorCode, String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
