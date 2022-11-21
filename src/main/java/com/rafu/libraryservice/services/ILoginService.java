package com.rafu.libraryservice.services;

import com.rafu.libraryservice.models.vo.requests.LoginRequest;
import com.rafu.libraryservice.models.vo.responses.TokenResponse;

public interface ILoginService {
  TokenResponse login(LoginRequest request);

  boolean isAuthorized(final String token);
}
