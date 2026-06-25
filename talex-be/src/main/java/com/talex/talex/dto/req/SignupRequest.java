package com.talex.talex.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;


@Builder
public record SignupRequest(

        @NotBlank(message = "Username is required")
        @Size(max = 30, message = "Username cannot be longer than 30 charactor")
        String username,

        @NotBlank(message = "Email is required")
        @Email(message = "Email is invalid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 20, message = "Password should be between 6 to 20 charactor long")
        String password
) {
}
