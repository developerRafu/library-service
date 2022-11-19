package com.rafu.libraryservice.configs;

import com.rafu.libraryservice.domain.Book;
import com.rafu.libraryservice.services.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbConfigs {
    private final IBookService bookService;

    @Bean
    public boolean createBook() {
        final var book = Book.builder().name("Clean Code").build();
        bookService.create(book);
        return true;
    }
}
