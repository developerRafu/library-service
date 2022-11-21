package com.rafu.libraryservice.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.rafu.libraryservice.erros.BookAlreadyCreate;
import com.rafu.libraryservice.erros.NotFoundException;
import com.rafu.libraryservice.models.domain.Book;
import com.rafu.libraryservice.repositories.BookRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BookServiceImplTest {
  IBookService service;
  BookRepository repository;

  @BeforeEach
  void setUp() {
    repository = mock(BookRepository.class);
    service = new BookServiceImpl(repository);
  }

  @Nested
  class createTests {
    @Test
    void shouldReturnABook() {
      final var book = getBook();
      when(repository.save(any())).thenReturn(book);
      final var result = service.create(book);
      assertEquals(book, result);
    }

    @Test
    void shouldThrowsException() {
      final var book = getBook();
      when(repository.findByName(any())).thenReturn(Optional.of(book));
      assertThrows(BookAlreadyCreate.class, () -> service.create(book));
    }
  }

  @Nested
  class updateTests {
    @Test
    void shouldUpdateABook() {
      final var book = getBook();
      when(repository.save(any())).thenReturn(book);
      when(repository.findById(any())).thenReturn(Optional.of(book));
      final var result = service.update(1L, book);
      assertEquals(book, result);
    }

    @Test
    void shouldThrowsExceptionWhenBookNotFound() {
      final var book = getBook();
      when(repository.findById(any())).thenReturn(Optional.ofNullable(null));
      assertThrows(NotFoundException.class, () -> service.update(1L, book));
    }

    @Test
    void shouldThrowsExceptionWhenNameExists() {
      final var book = getBook();
      when(repository.findById(any())).thenReturn(Optional.of(book));
      when(repository.findByName(any())).thenReturn(Optional.of(book));
      assertThrows(BookAlreadyCreate.class, () -> service.update(1L, book));
    }
  }

  private Book getBook() {
    return Book.builder().name("Clean code").id(1L).build();
  }
}
