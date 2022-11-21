package com.rafu.libraryservice.services;

import com.rafu.libraryservice.models.domain.User;
import java.util.Optional;

public interface IUserService {
  Optional<User> findByEmail(final String email);

  User create(final User user);
}
