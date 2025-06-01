package com.example.libraryapi.repository;

import com.example.libraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books with titles containing the given string
//    List<Book> findByTitleContaining(String titlePart);

    // Find books by the exact author name
//    List<Book> findByAuthor(String author);
}
