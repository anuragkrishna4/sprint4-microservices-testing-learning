package com.nisum.book.controller;

import com.nisum.book.exception.BookNotFoundException;
import com.nisum.book.model.Book;
import com.nisum.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleNotFound(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDetails("NOT_FOUND", ex.getMessage()));
    }


    public record ErrorDetails(String error, String message) {}
}
