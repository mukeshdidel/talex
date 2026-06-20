package com.talex.talex.dto.res;

import com.talex.talex.entity.Role;

public record LoginResponse(
        String username,
        String email,
        Role role,

        String token
) {
}
