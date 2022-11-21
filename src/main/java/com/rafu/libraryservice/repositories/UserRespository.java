package com.rafu.libraryservice.repositories;

import com.rafu.libraryservice.models.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(final String email);
}
