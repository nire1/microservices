package com.tsaidenis.mainservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsaidenis.mainservice.dto.BookDto;
import com.tsaidenis.mainservice.kafka.KafkaProducer;
import com.tsaidenis.mainservice.sevice.BookService;
import com.tsaidenis.mainservice.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private KafkaProducer kafkaProducer;

    @Autowired
    private ObjectMapper objectMapper;

    private BookDto bookDto;

    @BeforeEach
    public void setUp() {
        bookDto = new BookDto()
                .setAuthor_name("Othilia Echelle")
                .setCategory("DRAMA")
                .setTitle("TestBook");
    }

    @Test
    public void testCreate() throws Exception {
        doNothing().when(bookService).createBook(any(BookDto.class));
        doNothing().when(kafkaProducer).sendMessage(anyString());

        mockMvc.perform(post("/books/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDto)))
                .andExpect(status().isOk());

        verify(bookService, times(1)).createBook(any(BookDto.class));
        verify(kafkaProducer, times(1)).sendMessage(anyString());
    }

    @Test
    public void testGetAll() throws Exception {
        when(bookService.getAll()).thenReturn(Collections.singletonList(bookDto));
        doNothing().when(kafkaProducer).sendMessage(anyString());

        mockMvc.perform(get("/books/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Test Book"));

        verify(bookService, times(1)).getAll();
        verify(kafkaProducer, times(1)).sendMessage(anyString());
    }

    @Test
    public void testGet() throws Exception {
        when(bookService.getById(1L)).thenReturn(bookDto);
        doNothing().when(kafkaProducer).sendMessage(anyString());

        mockMvc.perform(get("/books/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Book"));

        verify(bookService, times(1)).getById(1L);
        verify(kafkaProducer, times(1)).sendMessage(anyString());
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(bookService).delete(1L);
        doNothing().when(kafkaProducer).sendMessage(anyString());

        mockMvc.perform(delete("/books/{id}", 1L))
                .andExpect(status().isOk());

        verify(bookService, times(1)).delete(1L);
        verify(kafkaProducer, times(1)).sendMessage(anyString());
    }

    @Test
    public void testUpdateName() throws Exception {
        doNothing().when(bookService).update(1L, "Updated Title");
        doNothing().when(kafkaProducer).sendMessage(anyString());

        mockMvc.perform(put("/books/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("Updated Title"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).update(1L, "Updated Title");
        verify(kafkaProducer, times(1)).sendMessage(anyString());
    }

}