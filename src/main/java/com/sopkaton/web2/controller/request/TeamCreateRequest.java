package com.sopkaton.web2.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TeamCreateRequest(
        @Size(max = 5)
        @NotNull
        String name,
        @NotNull
        int minimumStartNumber
) {
}
