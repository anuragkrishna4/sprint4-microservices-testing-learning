package com.nisum.book.service;

import com.nisum.book.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Override
    public Book getBookById(Long id) {
        return Book.builder()
                .id(id)
                .title("Default Book")
                .author("Unknown Author")
                .build();
    }
}
