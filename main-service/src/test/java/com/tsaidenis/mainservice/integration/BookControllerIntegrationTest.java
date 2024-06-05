package com.tsaidenis.mainservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsaidenis.mainservice.dto.BookDto;
import com.tsaidenis.mainservice.entity.Author;
import com.tsaidenis.mainservice.entity.Book;
import com.tsaidenis.mainservice.entity.Category;
import com.tsaidenis.mainservice.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;

    private BookDto bookDto;
    private Book book;

    @BeforeEach
    public void setUp() {
        bookRepository.deleteAll();

        Author author = new Author()
                .setId(1L)
                .setName("Othilia Echelle");

        book = new Book()
                .setId(1L)
                .setCategory(Category.DRAMA)
                .setTitle("TestBook")
                .setAuthor(author);

        bookDto = new BookDto()
                .setAuthor_name("Othilia Echelle")
                .setCategory("DRAMA")
                .setTitle("TestBook");
    }

    @Test
    public void testCreateBook() throws Exception {
        mockMvc.perform(post("/books/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk());

        assertEquals(1, bookRepository.findAll().size());
    }

    @Test
    public void testGetAllBooks() throws Exception {
        bookRepository.save(book);

        mockMvc.perform(get("/books/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Test Book"));
    }

    @Test
    public void testGetBookById() throws Exception {
        Book savedBook = bookRepository.save(book);

        mockMvc.perform(get("/books/{id}", savedBook.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book savedBook = bookRepository.save(book);

        mockMvc.perform(delete("/books/{id}", savedBook.getId()))
                .andExpect(status().isOk());

        assertEquals(0, bookRepository.findAll().size());
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book savedBook = bookRepository.save(book);
        String updatedTitle = "Updated Test Book";

        mockMvc.perform(put("/books/{id}", savedBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTitle))
                .andExpect(status().isOk());

        Book updatedBook = bookRepository.findById(savedBook.getId()).orElseThrow();
        assertEquals(updatedTitle, updatedBook.getTitle());
    }
}