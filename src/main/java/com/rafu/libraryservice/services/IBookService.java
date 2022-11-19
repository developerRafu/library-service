package com.rafu.libraryservice.services;

import com.rafu.libraryservice.domain.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Book create(final Book book);

    Book update(Long id, final Book book);

    Optional<Book> findById(final Long id);

    List<Book> findAll();

    void delete(final Long id);

    Optional<Book> findByName(final String name);
}
