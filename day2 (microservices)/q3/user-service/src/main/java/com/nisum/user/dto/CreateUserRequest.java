package com.nisum.user.dto;

import jakarta.validation.constraints.*;

public record CreateUserRequest (
    @NotBlank
    String name,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String phone

){}
