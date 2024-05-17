package com.tsaidenis.mainservice.web;

import com.tsaidenis.mainservice.entity.Book;
import com.tsaidenis.mainservice.kafka.KafkaProducer;
import com.tsaidenis.mainservice.repository.BookRepository;
import com.tsaidenis.mainservice.sevice.BookService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    BookRepository bookRepository;
    BookService bookService;
    private final KafkaProducer kafkaProducer;
    public BookController(BookRepository bookRepository, KafkaProducer kafkaProducer,BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.kafkaProducer = kafkaProducer;

    }

    @PostMapping("/create")
    public void create(@RequestBody Book book){
        kafkaProducer.sendMessage(book.toString());
        bookService.create(book);

    }
    @GetMapping("/get")
    public List<Book> getAll(){
        List<Book> books = bookRepository.findAll();
        kafkaProducer.sendMessage(books.toString());
        return books;
    }
}
