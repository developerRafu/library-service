package com.rafu.libraryservice.repositories;

import com.rafu.libraryservice.domain.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
  Optional<Book> findByName(String name);
}
