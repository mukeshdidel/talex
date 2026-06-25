package com.talex.talex.dto.res;

import com.talex.talex.entity.Role;
import lombok.Builder;


@Builder
public record SignupResponse (
        String username,
        String email,
        Role role
){
}
