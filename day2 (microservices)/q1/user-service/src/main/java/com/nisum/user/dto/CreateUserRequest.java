package com.nisum.user.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CreateUserRequest(
        @NotBlank String name,
        @Email String email,
        @Size(min = 1) List<@Valid AddressDTO> addresses
) {

    public record AddressDTO(
            @NotBlank String street,
            @NotBlank String city,
            @NotBlank String state,
            @Pattern(regexp = "\\d{6}") String zipCode   // Indian 6‑digit PIN
    ) { }
}
