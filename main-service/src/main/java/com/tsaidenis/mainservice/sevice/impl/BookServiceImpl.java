package com.tsaidenis.mainservice.sevice.impl;

import com.tsaidenis.mainservice.aspect.LogA;
import com.tsaidenis.mainservice.dto.BookDto;
import com.tsaidenis.mainservice.entity.Book;
import com.tsaidenis.mainservice.mapper.BookDtoToBookMapper;
import com.tsaidenis.mainservice.mapper.BookToBookDtoMapper;
import com.tsaidenis.mainservice.repository.BookRepository;
import com.tsaidenis.mainservice.sevice.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookDtoToBookMapper mapper;
    private final BookToBookDtoMapper bookToBookDtoMapper;

    public BookServiceImpl(BookRepository bookRepository, BookDtoToBookMapper mapper, BookToBookDtoMapper bookToBookDtoMapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
        this.bookToBookDtoMapper = bookToBookDtoMapper;
    }

    @Override
    @LogA
    public void createBook(BookDto bookDto) {
        bookRepository.save(mapper.map(bookDto));
    }

    @Override
    @LogA
    public BookDto getById(Long id) {
        if (bookRepository.existsById(id)) {
            Book book = bookRepository.findBookById(id);
            return bookToBookDtoMapper.map(book);
        }else throw new NoSuchElementException("Не найден");
    }

    @Override
    @LogA
    public List<BookDto> getAll() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDto> list = bookList.stream()
                .map(bookToBookDtoMapper::map)
                .toList();
        return list;
    }

    @Override
    @LogA
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @LogA
    public void update(Long id, String name) {
        Book book = bookRepository.findBookById(id);
        book.setTitle(name);
        bookRepository.save(book);
    }
}
