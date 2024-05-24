package com.tsaidenis.mainservice.mapper.impl;

import com.tsaidenis.mainservice.dto.BookDto;
import com.tsaidenis.mainservice.entity.Author;
import com.tsaidenis.mainservice.entity.Book;
import com.tsaidenis.mainservice.entity.Category;
import com.tsaidenis.mainservice.mapper.BookToBookDtoMapper;
import com.tsaidenis.mainservice.sevice.AuthorService;
import org.springframework.stereotype.Component;

@Component
public class BookToBookDtoMapperImpl implements BookToBookDtoMapper {
    private final AuthorService authorService;

    public BookToBookDtoMapperImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public BookDto map(Book book) {
        return new BookDto()
                .setTitle(book.getTitle())
                .setCategory(String.valueOf(book.getCategory()))
                .setAuthor_name(book.getAuthor().getName());
    }
}
