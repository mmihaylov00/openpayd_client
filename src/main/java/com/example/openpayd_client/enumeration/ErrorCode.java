package com.example.openpayd_client.enumeration;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    INTERNAL_ERROR(1000),
    EXTERNAL_API_ERROR(1001),
    EXTERNAL_API_BAD_REQUEST(1002),
    UNAUTHORIZED(1003),
    ;
    private final int code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(int code) {
        this(code, "Something went wrong");
    }

    ErrorCode(int code, String message) {
        this(code, message, HttpStatus.BAD_REQUEST);
    }

    ErrorCode(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
