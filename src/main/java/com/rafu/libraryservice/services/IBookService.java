package com.rafu.libraryservice.services;

import com.rafu.libraryservice.domain.Book;
import com.rafu.libraryservice.models.BookFilter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Book create(final Book book);

    Book update(Long id, final Book book);

    Optional<Book> findById(final Long id);

    Page<Book> findAll(final BookFilter filter);

    void delete(final Long id);

    Optional<Book> findByName(final String name);
}
