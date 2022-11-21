package com.rafu.libraryservice.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.rafu.libraryservice.erros.EmailInUseException;
import com.rafu.libraryservice.helpers.UserHelper;
import com.rafu.libraryservice.repositories.UserRespository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceImplTest {
  UserRespository respository;
  IUserService service;

  @BeforeEach
  void setUp() {
    respository = mock(UserRespository.class);
    service = new UserServiceImpl(respository);
  }

  @Test
  void shouldReturnAValidUser() {
    final var user = UserHelper.getUser();
    when(respository.findByEmail(any())).thenReturn(Optional.empty());
    when(respository.save(any())).thenReturn(user);
    final var result = service.create(user);
    assertEquals(user, result);
  }

  @Test
  void shouldThrowsExceptionWhenUserDoesNotExists() {
    when(respository.findByEmail(any())).thenReturn(Optional.of(UserHelper.getUser()));
    assertThrows(EmailInUseException.class, () -> service.create(UserHelper.getUser()));
  }
}
