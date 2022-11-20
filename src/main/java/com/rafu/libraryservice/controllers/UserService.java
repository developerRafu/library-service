package com.rafu.libraryservice.controllers;

import com.rafu.libraryservice.domain.User;
import com.rafu.libraryservice.services.IUserService;
import com.rafu.libraryservice.vo.requests.UserRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@OpenAPIDefinition
@Tag(name = "User rest controller")
public class UserService {
    private final IUserService service;
    private final ModelMapper mapper;

    @PostMapping
    @Operation(summary = "Create new user")
    public ResponseEntity<Void> post(@RequestBody final UserRequest request) {
        final var user = service.create(toUser(request));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    private User toUser(final UserRequest request) {
        return mapper.map(request, User.class);
    }
}
