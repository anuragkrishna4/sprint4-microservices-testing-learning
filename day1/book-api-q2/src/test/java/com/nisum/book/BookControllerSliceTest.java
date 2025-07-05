package com.nisum.book;

import com.nisum.book.controller.BookController;
import com.nisum.book.dto.BookDto;
import com.nisum.book.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerSliceTest {

    @Autowired MockMvc mockMvc;

    @MockBean BookService bookService;

    @WithMockUser
    @Test
    void testGetBooks() throws Exception {

        BookDto book = new BookDto(1L, "Java", "Author", BigDecimal.valueOf(150));
        Mockito.when(bookService.list()).thenReturn(List.of(book));


        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Java"));
    }
}
