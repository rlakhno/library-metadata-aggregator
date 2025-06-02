package com.example.libraryapi.repository;

import com.example.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM books WHERE LOWER(title) LIKE %:keyword% OR LOWER(description) LIKE %:keyword%", nativeQuery = true)
    List<Book> searchByKeyword(@Param("keyword") String keyword);

    // Find books with titles containing the given string
//    List<Book> findByTitleContaining(String titlePart);

    // Find books by the exact author name
//    List<Book> findByAuthor(String author);
}
