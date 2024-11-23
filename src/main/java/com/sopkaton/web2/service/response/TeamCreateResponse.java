package com.sopkaton.web2.service.response;

import jakarta.validation.constraints.NotNull;

public record TeamCreateResponse(
        @NotNull
        Long id,
        @NotNull
        String name,
        @NotNull
        String password
) {
}
