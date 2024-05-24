package com.tsaidenis.mainservice.utils;

import com.tsaidenis.mainservice.dto.BookDto;
import com.tsaidenis.mainservice.entity.Author;
import com.tsaidenis.mainservice.entity.Book;
import com.tsaidenis.mainservice.entity.Category;

public class DataUtils {
    public static BookDto getBookDto(){
        return new BookDto()
                .setAuthor_name("Othilia Echelle")
                .setCategory("DRAMA")
                .setTitle("TestBook");
    }
    public static Book getBookEntity(){
        return new Book()
                .setId(1L)
                .setCategory(Category.DRAMA)
                .setTitle("TestBook")
                .setAuthor(getAuthorEntity());
    }
    public static Author getAuthorEntity(){
        return new Author()
                .setId(1L)
                .setName("Othilia Echelle");
    }
}
