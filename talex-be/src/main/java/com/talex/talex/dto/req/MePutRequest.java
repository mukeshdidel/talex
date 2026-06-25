package com.talex.talex.dto.req;

import lombok.Builder;

@Builder
public record MePutRequest(
        String name,
        String bio
) {
}
