package com.rafu.libraryservice.services;

import com.rafu.libraryservice.vo.requests.LoginRequest;
import com.rafu.libraryservice.vo.responses.TokenResponse;

public interface ILoginService {
  TokenResponse login(LoginRequest request);

  boolean isAuthorized(final String token);
}
