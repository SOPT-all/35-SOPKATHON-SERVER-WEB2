package com.sopkaton.web2.controller;


import com.sopkaton.web2.common.api.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.sopkaton.web2.common.api.CustomException;
import com.sopkaton.web2.common.api.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ApiResponse.fail(ErrorCode.BAD_REQUEST);
    }
    @ExceptionHandler(CustomException.class)
    public ApiResponse<Void> handleCustomException(CustomException e) {
        return ApiResponse.fail(e.getErrorCode());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.info(String.valueOf(e.getClass()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}

