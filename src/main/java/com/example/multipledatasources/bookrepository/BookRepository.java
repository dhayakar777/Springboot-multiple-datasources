package com.example.multipledatasources.bookrepository;

import com.example.multipledatasources.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface BookRepository extends JpaRepository<Book, Long> {
}