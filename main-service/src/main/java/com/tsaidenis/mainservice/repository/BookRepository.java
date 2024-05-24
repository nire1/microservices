package com.tsaidenis.mainservice.repository;

import com.tsaidenis.mainservice.dto.BookDto;
import com.tsaidenis.mainservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

@Query("from Book b join fetch b.author")
    List<Book> findAll();
@Query("from Book b join fetch b.author where b.id=:id")
    Book findBookById(@Param("id") Long id);
}
