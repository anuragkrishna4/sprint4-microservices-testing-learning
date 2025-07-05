package com.nisum.book.controller;

import com.nisum.book.dto.BookDto;
import com.nisum.book.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;
    public BookController(BookService s) { this.service = s; }

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public BookDto create(@Valid @RequestBody BookDto dto) { return service.create(dto); }

    @GetMapping("/{id}")
    public BookDto get(@PathVariable Long id) { return service.get(id); }

    @GetMapping
    public List<BookDto> list() { return service.list(); }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id, @Valid @RequestBody BookDto dto) { return service.update(id, dto); }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
