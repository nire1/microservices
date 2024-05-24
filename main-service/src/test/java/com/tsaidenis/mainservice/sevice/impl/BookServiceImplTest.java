package com.tsaidenis.mainservice.sevice.impl;

import com.tsaidenis.mainservice.dto.BookDto;
import com.tsaidenis.mainservice.entity.Author;
import com.tsaidenis.mainservice.entity.Book;
import com.tsaidenis.mainservice.entity.Category;
import com.tsaidenis.mainservice.mapper.BookDtoToBookMapper;
import com.tsaidenis.mainservice.mapper.BookToBookDtoMapper;
import com.tsaidenis.mainservice.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookDtoToBookMapper mapper;
    @Mock
    private BookToBookDtoMapper bookToBookDtoMapper;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void createBook(){
        //given
        BookDto bookDto = new BookDto()
                .setAuthor_name("Othilia Echelle")
                .setCategory("DRAMA")
                .setTitle("TestBook");
        Book book = new Book()
                .setId(1L)
                .setCategory(Category.DRAMA)
                .setTitle("TestBook")
                .setAuthor(new Author().setId(1L).setName("Othilia Echelle"));
        BDDMockito.doNothing().when(bookRepository.save(book));
        //when
        BDDMockito.when(mapper.map(bookDto)).thenReturn(book);

        verify(bookRepository,times(1))
                .save(book);
        verify(mapper, Mockito.times(1))
                .map(any());
    }

}