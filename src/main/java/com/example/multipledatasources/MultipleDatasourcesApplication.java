package com.example.multipledatasources;

import com.example.multipledatasources.book.Book;
import com.example.multipledatasources.bookrepository.BookRepository;
import com.example.multipledatasources.user.User;
import com.example.multipledatasources.userrepository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class MultipleDatasourcesApplication {


    private final UserRepository userRepository;


    private final BookRepository bookRepository;

    public MultipleDatasourcesApplication(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void addData2DB() {
        userRepository.saveAll(Stream.of(new User(755L, "John"), new User(678L, "Dhaya")).
                collect(Collectors.toList()));
        bookRepository.saveAll(Stream.of(new Book(123L, "PiRates Of Carribean"),
                new Book(987L, "The Greatest Man")).collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        SpringApplication.run(MultipleDatasourcesApplication.class, args);
    }

}
