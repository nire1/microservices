package com.tsaidenis.mainservice.web;

import com.tsaidenis.mainservice.dto.BookDto;
import com.tsaidenis.mainservice.entity.Book;
import com.tsaidenis.mainservice.kafka.KafkaProducer;
import com.tsaidenis.mainservice.sevice.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Books api",
        description = "Поиск по id,удаление,создание и получение всего списка.")
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final KafkaProducer kafkaProducer;

    public BookController(BookService bookService, KafkaProducer kafkaProducer) {
        this.bookService = bookService;
        this.kafkaProducer = kafkaProducer;
    }


    @PostMapping("/create")
    @Operation(summary = "Создание книги")
    public void create(@RequestBody @Valid BookDto bookDto) {

        bookService.createBook(bookDto);
        kafkaProducer.sendMessage("создание книги " + bookDto.toString());
    }

    @GetMapping("/getAll")
    @Operation(summary = "Получение всех книг")
    public List<BookDto> getAll() {
        kafkaProducer.sendMessage("получение списка книг");
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение книги по id")
    public BookDto get(@PathVariable Long id) {
        kafkaProducer.sendMessage("получение книги по id " + id);
        return bookService.getById(id);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление книги")
    public void delete(@PathVariable Long id) {
        kafkaProducer.sendMessage("удаление книги " + id);
        bookService.delete(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление книги")
    public void updateName(@PathVariable Long id,
                           @RequestBody String name) {
        kafkaProducer.sendMessage("обновление книги " + id);
        bookService.update(id, name);
    }

}
