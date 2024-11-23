package com.sopkaton.web2.service.response;

public record IdResponse(
        Long id
) {
    public static IdResponse of(final Long id) {
        return new IdResponse(id);
    }
}


