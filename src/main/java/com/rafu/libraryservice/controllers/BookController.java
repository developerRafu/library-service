package com.rafu.libraryservice.controllers;

import com.rafu.libraryservice.domain.Book;
import com.rafu.libraryservice.erros.NotFoundException;
import com.rafu.libraryservice.models.BookFilter;
import com.rafu.libraryservice.services.IBookService;
import com.rafu.libraryservice.vo.requests.BookRequest;
import com.rafu.libraryservice.vo.responses.BookResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
            @RequestHeader(name = "Authorization") final String authorization,
            @PathVariable final Long id) {
        return service
                .findById(id)
                .map(b -> ResponseEntity.ok(this.toResponse(b)))
                .orElseThrow(() -> new NotFoundException("Book"));
    }

    @GetMapping
    @Operation(summary = "Retrieve all books information")
    public ResponseEntity<Page<BookResponse>> findAll(
            @RequestHeader(name = "Authorization") final String authorization,
            @RequestParam(required = false, value = "page", defaultValue = "0") final int page,
            @RequestParam(required = false, value = "size", defaultValue = "10") final int size,
            @RequestParam(required = false, value = "name") final String name,
            @RequestParam(required = false, value = "id") final Long id) {
        final var filter = BookFilter
                .builder()
                .name(name)
                .id(id)
                .build();
        final var pageResponse = this.service.findAll(filter).map(this::toResponse);
        return ResponseEntity.ok()
                .body(pageResponse);
    }

    @PostMapping
    @Operation(summary = "Create a book")
    public ResponseEntity<BookResponse> post(
            @RequestHeader(name = "Authorization") final String authorization,
            @RequestBody @Valid final BookRequest request) {
        final var book = service.create(toBook(request));
        return ResponseEntity.ok().body(this.toResponse(book));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book")
    public ResponseEntity<BookResponse> put(
            @RequestHeader(name = "Authorization") final String authorization,
            @PathVariable final Long id,
            @RequestBody final BookRequest request) {
        final var book = service.update(id, toBook(request));
        return ResponseEntity.ok().body(this.toResponse(book));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book")
    public ResponseEntity<BookResponse> delete(
            @RequestHeader(name = "Authorization") final String authorization,
            @PathVariable final Long id) {
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
