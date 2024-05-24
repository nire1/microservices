package com.tsaidenis.mainservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsaidenis.mainservice.dto.BookDto;
import com.tsaidenis.mainservice.kafka.KafkaProducer;
import com.tsaidenis.mainservice.sevice.BookService;
import com.tsaidenis.mainservice.utils.DataUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BookService bookService;
    @MockBean
    private KafkaProducer kafkaProducer;

    @Test
    public void create() throws Exception {
        BookDto bookDto = DataUtils.getBookDto();

        ResultActions result = mockMvc.perform(post("/books/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDto)));
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author_name", CoreMatchers.is("Othilia Echelle")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is("TestBook")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category", CoreMatchers.is("DRAMA")));

    }

}