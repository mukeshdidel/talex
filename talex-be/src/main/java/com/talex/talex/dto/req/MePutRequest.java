package com.talex.talex.dto.req;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record MePutRequest(
        @Size(max = 100, message = "Name cannot be longer than 100 characters")
        String name,

        @Size(max = 1000, message = "Bio cannot be longer than 1000 characters")
        String bio
) {
}
