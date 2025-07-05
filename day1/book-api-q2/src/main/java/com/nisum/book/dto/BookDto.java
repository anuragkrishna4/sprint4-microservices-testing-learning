package  com.nisum.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record BookDto(
        Long id,
        @NotBlank String title,
        @NotBlank String author,
        @Positive BigDecimal price
) {}
