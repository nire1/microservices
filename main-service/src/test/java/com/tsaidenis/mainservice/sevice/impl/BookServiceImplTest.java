package com.tsaidenis.mainservice.sevice.impl;

import com.tsaidenis.mainservice.dto.BookDto;
import com.tsaidenis.mainservice.entity.Author;
import com.tsaidenis.mainservice.entity.Book;
import com.tsaidenis.mainservice.entity.Category;
import com.tsaidenis.mainservice.mapper.BookDtoToBookMapper;
import com.tsaidenis.mainservice.mapper.BookToBookDtoMapper;
import com.tsaidenis.mainservice.repository.BookRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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
    private static BookDto bookDto;
    private static Book book;
    @BeforeAll
    static void setUp() {
         bookDto = new BookDto()
                .setAuthor_name("Othilia Echelle")
                .setCategory("DRAMA")
                .setTitle("TestBook");
         book = new Book()
                .setId(1L)
                .setCategory(Category.DRAMA)
                .setTitle("TestBook")
                .setAuthor(new Author().setId(1L).setName("Othilia Echelle"));
    }

    @Test
    public void createBook(){

        BDDMockito.when(mapper.map(any(BookDto.class))).thenReturn(book);
        bookService.createBook(bookDto);
        verify(bookRepository,times(1))
                .save(book);
    }
    @Test
    public void getBookById_shouldReturnBook(){
        when(bookRepository.existsById(1L)).thenReturn(true);
        when(bookRepository.findBookById(1L)).thenReturn(book);
        when(bookToBookDtoMapper.map(book)).thenReturn(bookDto);
        BookDto result = bookService.getById(1L);
        assertEquals(bookDto,result);
    }
    @Test
    public void getBookById_shouldReturnNull(){
        when(bookRepository.existsById(1L)).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> bookService.getById(1L));
    }
    @Test
    public void getAll_shouldReturnListOfBooks(){
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));
        when(bookToBookDtoMapper.map(book)).thenReturn(bookDto);

        List<BookDto> result = bookService.getAll();

        assertEquals(1,result.size());
        assertEquals(bookDto,result.get(0));
    }
    @Test
    public void deleteBookById(){
        bookService.delete(1L);
        verify(bookRepository,times(1)).deleteById(1L);
    }
    @Test
    public void updateBook(){
        when(bookRepository.findBookById(1L)).thenReturn(book);
        bookService.update(1L,"Updated title");
        assertEquals("Updated title",bookDto.getTitle());
        verify(bookRepository,times(1)).save(book);
    }
}