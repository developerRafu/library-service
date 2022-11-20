package com.rafu.libraryservice.repositories;

import com.rafu.libraryservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(final String email);
}
