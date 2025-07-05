package com.nisum.book.service;

import com.nisum.book.dto.BookDto;
import com.nisum.book.exception.ResourceNotFoundException;
import com.nisum.book.model.Book;
import com.nisum.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) { this.repo = repo; }

    public BookDto create(BookDto dto) { return toDto(repo.save(toEntity(dto))); }

    public BookDto get(Long id) {
        return toDto(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    public List<BookDto> list() { return repo.findAll().stream().map(this::toDto).toList(); }

    public BookDto update(Long id, BookDto dto) {
        Book book = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setPrice(dto.price());
        return toDto(repo.save(book));
    }

    public void delete(Long id) { repo.deleteById(id); }

    /* mapping helpers */
    private BookDto toDto(Book b) { return new BookDto(b.getId(), b.getTitle(), b.getAuthor(), b.getPrice()); }
    private Book toEntity(BookDto d) {
        Book b = new Book();
        b.setTitle(d.title());
        b.setAuthor(d.author());
        b.setPrice(d.price());
        return b;
    }
}
