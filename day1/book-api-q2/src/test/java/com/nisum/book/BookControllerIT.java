package com.nisum.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.book.dto.BookDto;
import com.nisum.book.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIT {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @Test
    void testCreateBook() throws Exception {
        BookDto book = new BookDto(null, "Spring Boot", "Nisum", BigDecimal.valueOf(299));
        String token = "Bearer " + JwtUtil.generate("test-user");

        mockMvc.perform(post("/api/books")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Spring Boot"));
    }
}
