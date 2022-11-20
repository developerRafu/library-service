package com.rafu.libraryservice.controllers;

import com.rafu.libraryservice.erros.UnauthorizedException;
import com.rafu.libraryservice.services.ILoginService;
import com.rafu.libraryservice.vo.requests.LoginRequest;
import com.rafu.libraryservice.vo.responses.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {
    private final ILoginService service;

    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody final LoginRequest request) {
        return ResponseEntity.ok().body(service.login(request));
    }

    @Valid
    @GetMapping
    public ResponseEntity<Boolean> get(@RequestHeader(required = true, name = "Authorization") @NotNull final String authorization) {
        final var isAuthorized = service.isAuthorized(authorization);
        if(!isAuthorized){
            throw new UnauthorizedException();
        }
        return ResponseEntity.ok().body(Boolean.TRUE);
    }
}
