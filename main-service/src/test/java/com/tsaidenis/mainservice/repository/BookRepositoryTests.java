package com.tsaidenis.mainservice.repository;

import com.tsaidenis.mainservice.MainServiceApplication;
import com.tsaidenis.mainservice.entity.Author;
import com.tsaidenis.mainservice.entity.Book;
import com.tsaidenis.mainservice.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
//@ContextConfiguration(classes = MainServiceApplication.class)

@DataJpaTest
class BookRepositoryTests {
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setup(){
        bookRepository.deleteAll();
    }

    @Test
    @DisplayName("Test save book functionality")
    public void givenBookObject_whenSave_thenBookIsCreated(){
        //given
        Book bookToSave = new Book()
                .setTitle("TestBook")
                .setAuthor(new Author().setName("TestAuthor"))
                .setCategory(Category.ADVENTURE);
        //when
        Book savedBook = bookRepository.save(bookToSave);
        //then
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isNotNull();

    }
}