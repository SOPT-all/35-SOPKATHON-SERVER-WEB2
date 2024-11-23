package com.sopkaton.web2.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sopkaton.web2.common.api.ErrorCode;
import com.sopkaton.web2.common.api.SuccessCode;
import lombok.Getter;


@Getter
public class ApiResponse<T> {

    private final int code;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T data;

    public ApiResponse(int code) {
        this.code = code;
    }

    public ApiResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(SuccessCode success) {
        return new ApiResponse<>(success.getHttpStatus().value());
    }


    public static <T> ApiResponse<T> success(SuccessCode success, T data) {
        return new ApiResponse<>(success.getHttpStatus().value(), data);
    }

    public static <T> ApiResponse<T> fail(ErrorCode error) {
        return new ApiResponse<>(error.getCustomErrorCode());
    }
}
