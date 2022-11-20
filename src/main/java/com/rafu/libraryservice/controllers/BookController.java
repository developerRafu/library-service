package com.rafu.libraryservice.controllers;

import com.rafu.libraryservice.domain.Book;
import com.rafu.libraryservice.erros.NotFoundException;
import com.rafu.libraryservice.services.IBookService;
import com.rafu.libraryservice.vo.requests.BookRequest;
import com.rafu.libraryservice.vo.responses.BookResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/books", name = "Books Rest Controller")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@OpenAPIDefinition
@Tag(name = "Book rest controller")
public class BookController {
    private final IBookService service;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a book information")
    public ResponseEntity<BookResponse> findById(
            @RequestHeader(required = true, name = "Authorization") final String authorization,
            @PathVariable final Long id
    ) {
        return service.findById(id).map(b -> ResponseEntity.ok(this.toResponse(b))).orElseThrow(() -> new NotFoundException("Book"));
    }

    @GetMapping
    @Operation(summary = "Retrieve all books information")
    public ResponseEntity<List<BookResponse>> findAll(@RequestHeader(required = true, name = "Authorization") final String authorization) {
        return ResponseEntity.ok().body(
                this.service
                        .findAll()
                        .stream()
                        .map(this::toResponse)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    @Operation(summary = "Create a book")
    public ResponseEntity<BookResponse> post(
            @RequestHeader(required = true, name = "Authorization") final String authorization,
            @RequestBody final BookRequest request
    ) {
        final var book = service.create(toBook(request));
        return ResponseEntity.ok().body(this.toResponse(book));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book")
    public ResponseEntity<BookResponse> put(
            @RequestHeader(required = true, name = "Authorization") final String authorization,
            @PathVariable final Long id,
            @RequestBody final BookRequest request
    ) {
        final var book = service.update(id, toBook(request));
        return ResponseEntity.ok().body(this.toResponse(book));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book")
    public ResponseEntity<BookResponse> delete(
            @RequestHeader(required = true, name = "Authorization") final String authorization,
            @PathVariable final Long id
    ) {
        service.delete(id);
        return ResponseEntity.accepted().build();
    }

    private BookResponse toResponse(final Book book) {
        return mapper.map(book, BookResponse.class);
    }

    private Book toBook(final BookRequest request) {
        return mapper.map(request, Book.class);
    }
}
