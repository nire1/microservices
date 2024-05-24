package com.tsaidenis.mainservice.mapper.impl;

import com.tsaidenis.mainservice.dto.BookDto;
import com.tsaidenis.mainservice.entity.Author;
import com.tsaidenis.mainservice.entity.Book;
import com.tsaidenis.mainservice.entity.Category;
import com.tsaidenis.mainservice.mapper.BookDtoToBookMapper;
import com.tsaidenis.mainservice.mapper.BookToBookDtoMapper;
import com.tsaidenis.mainservice.sevice.AuthorService;
import org.springframework.stereotype.Component;

@Component
public class BookDtoToBookMapperImpl implements BookDtoToBookMapper {
    private final AuthorService authorService;

    public BookDtoToBookMapperImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public Book map(BookDto bookDto) {
        Author author = authorService.findByName(bookDto.getAuthor_name());
        return new Book()
                .setTitle(bookDto.getTitle())
                .setAuthor(author)
                .setCategory(Category.valueOf(bookDto.getCategory()));
    }
}
