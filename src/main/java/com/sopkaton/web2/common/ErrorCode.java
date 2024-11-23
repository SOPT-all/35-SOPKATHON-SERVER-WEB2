package com.sopkaton.web2.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST(40000),
    INVALID_PHONE_NUMBER(40000),
    INVALID_PASSWORD(40001);

    private final int customErrorCode;
}
