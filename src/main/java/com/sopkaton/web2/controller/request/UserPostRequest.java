package com.sopkaton.web2.controller.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserPostRequest(
        @NotNull
        String phoneNumber,
        @Size(min=6, max=6)
        @NotNull
        String password
) {
}
