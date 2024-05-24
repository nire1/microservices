package com.tsaidenis.mainservice.sevice;

import com.tsaidenis.mainservice.dto.BookDto;

import java.util.List;

public interface BookService {
    void createBook(BookDto bookDto);

    BookDto getById(Long id);

    List<BookDto> getAll();
    void delete(Long id);

    void update(Long id,String name);
}
