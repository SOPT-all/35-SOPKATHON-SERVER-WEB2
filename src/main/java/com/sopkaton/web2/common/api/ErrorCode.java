package com.sopkaton.web2.common.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST(40000),
    INVALID_PHONE_NUMBER(40000),
    INVALID_PASSWORD(40001),
    USER_NOT_FOUND(40401),
    MISSION_NOT_FOUND(40402),
    USER_TEAM_NOT_FOUND(40403),
    TEAM_NOT_FOUND(40400),
    DUPLICATE_TEAM_NAME(40900),
    INTERNAL_SERVER_ERROR(50000),
    NO_CHANCE(400000),
    CARD_NOT_FOUND(40404),
    PASSWORD_NOT_MATCH(40000),
    ALREADY_REGISTERED_TEAM(40900);


    private final int customErrorCode;
}
