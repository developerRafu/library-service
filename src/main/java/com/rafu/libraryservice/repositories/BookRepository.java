package com.rafu.libraryservice.repositories;

import com.rafu.libraryservice.models.domain.Book;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
  Optional<Book> findByName(String name);

  @Query(
      "SELECT b FROM Book b "
          + "WHERE 1=1 "
          + "AND (:name IS NULL OR b.name = :name) "
          + "AND (:id IS NULL OR b.id = :id)")
  Page<Book> findAllByFilter(final String name, final Pageable pageable);
}
