package com.rafu.libraryservice.controllers;

import com.rafu.libraryservice.erros.UnauthorizedException;
import com.rafu.libraryservice.services.ILoginService;
import com.rafu.libraryservice.vo.requests.LoginRequest;
import com.rafu.libraryservice.vo.responses.TokenResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@OpenAPIDefinition
@Tag(name = "Login rest controller")
public class LoginController {
  private final ILoginService service;

  @PostMapping
  @Operation(summary = "Log in")
  public ResponseEntity<TokenResponse> login(@RequestBody final LoginRequest request) {
    return ResponseEntity.ok().body(service.login(request));
  }

  @GetMapping
  @Operation(summary = "Validate token")
  public ResponseEntity<Boolean> get(
      @RequestHeader(required = true, name = "Authorization") final String authorization) {
    final var isAuthorized = service.isAuthorized(authorization);
    if (!isAuthorized) {
      throw new UnauthorizedException();
    }
    return ResponseEntity.ok().body(Boolean.TRUE);
  }
}
