package com.nisum.book.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) { super("Book %d not found".formatted(id)); }
}
