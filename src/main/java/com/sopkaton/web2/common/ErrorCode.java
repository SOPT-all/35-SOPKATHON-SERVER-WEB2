package com.sopkaton.web2.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    BAD_REQUEST(40000),
    USER_NOT_FOUND(40401),
    MISSION_NOT_FOUND(40402),
    USER_TEAM_NOT_FOUND(40403),
    TEAM_NOT_FOUND(40400);

    private final int customErrorCode;
}
