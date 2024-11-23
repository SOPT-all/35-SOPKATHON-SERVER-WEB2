package com.sopkaton.web2.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {
    OK(HttpStatus.OK), CREATED(HttpStatus.CREATED);

    private final HttpStatus httpStatus;
}
