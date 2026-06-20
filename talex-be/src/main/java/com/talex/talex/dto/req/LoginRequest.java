package com.talex.talex.dto.req;

public record LoginRequest(
        String usernameOrEmail,
        String password
) {
}
