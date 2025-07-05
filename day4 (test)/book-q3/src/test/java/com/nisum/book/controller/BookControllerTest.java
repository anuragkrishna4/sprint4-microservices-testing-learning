package com.nisum.book.controller;

import com.nisum.book.exception.BookNotFoundException;
import com.nisum.book.model.Book;
import com.nisum.book.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
@ExtendWith(RestDocumentationExtension.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("GET /api/books/1 → 200 OK with Book JSON")
    void getBookById_success() throws Exception {
        Book mockBook = new Book(1L, "Panchatantra", "PremChandra");

        when(bookService.getBookById(1L)).thenReturn(mockBook);

        mockMvc.perform(get("/api/books/1").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Panchatantra")))
                .andExpect(jsonPath("$.author", is("PremChandra")))
                .andDo(document("get-book-success"));
    }

    @Test
    @DisplayName("GET /api/books/42 → 404 Not Found with error body")
    void getBookById_notFound() throws Exception {
        when(bookService.getBookById(42L)).thenThrow(new BookNotFoundException(42L));

        mockMvc.perform(get("/api/books/42").accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("NOT_FOUND")))
                .andExpect(jsonPath("$.message", is("Book with ID 42 not found")))
                .andDo(document("get-book-not-found"));
    }
}
