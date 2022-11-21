package com.rafu.libraryservice.services;

import com.rafu.libraryservice.domain.Book;
import com.rafu.libraryservice.erros.BookAlreadyCreate;
import com.rafu.libraryservice.erros.NotFoundException;
import com.rafu.libraryservice.models.BookFilter;
import com.rafu.libraryservice.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookServiceImpl implements IBookService {
  private final BookRepository repository;

  @Override
  public Book create(final Book book) {
    final var bookFound = this.findByName(book.getName());
    if (bookFound.isPresent()) {
      throw new BookAlreadyCreate(book.getName());
    }
    return repository.save(book);
  }

  @Override
  public Book update(final Long id, final Book book) {
    book.setId(id);
    final var bookFound = this.findById(book.getId());

    if (bookFound.isEmpty()) {
      throw new NotFoundException(book.getName());
    }

    return create(book);
  }

  @Override
  public Optional<Book> findById(final Long id) {
    return repository.findById(id);
  }

  @Override
  public Page<Book> findAll(final BookFilter filter) {
    return repository.findAllByFilter(filter.getName(), filter.getRequest());
  }

  @Override
  public void delete(final Long id) {
    repository.deleteById(id);
  }

  @Override
  public Optional<Book> findByName(final String name) {
    return repository.findByName(name);
  }
}
