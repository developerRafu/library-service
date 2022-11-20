package com.rafu.libraryservice.configs;

import com.rafu.libraryservice.domain.Book;
import com.rafu.libraryservice.domain.User;
import com.rafu.libraryservice.services.IBookService;
import com.rafu.libraryservice.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DbConfigs {
    private final IBookService bookService;
    private final IUserService userService;

    @Bean
    public boolean createBook() {
        final var book = Book.builder().name("Clean Code").build();
        final var user = User
                .builder()
                .email("rafu@mail.com")
                .name("Rafu Henrique")
                .password("123456")
                .build();
        bookService.create(book);
        userService.create(user);
        return true;
    }
}
