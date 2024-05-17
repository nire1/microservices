package com.tsaidenis.mainservice.sevice;

import com.tsaidenis.mainservice.aspect.LogA;
import com.tsaidenis.mainservice.entity.Book;
import com.tsaidenis.mainservice.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
@LogA
    public void create(Book book) {
        bookRepository.save(book);
    }
}
