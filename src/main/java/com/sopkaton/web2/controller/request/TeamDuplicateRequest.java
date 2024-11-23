package com.sopkaton.web2.controller.request;

import jakarta.validation.constraints.NotNull;

public record TeamDuplicateRequest(
        @NotNull
        String name
) {
}
