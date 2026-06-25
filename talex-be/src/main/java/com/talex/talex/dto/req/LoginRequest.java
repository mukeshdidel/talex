package com.talex.talex.dto.req;

import lombok.Builder;

@Builder
public record LoginRequest(
        String usernameOrEmail,
        String password
) {
}
