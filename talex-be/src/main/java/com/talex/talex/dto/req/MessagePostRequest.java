package com.talex.talex.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MessagePostRequest(
        @NotBlank(message = "Message text is required")
        @Size(max = 2000, message = "Message text must be 2000 characters or less")
        String text
) {
}
