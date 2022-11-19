package com.rafu.libraryservice.controllers;

import com.rafu.libraryservice.domain.Book;
import com.rafu.libraryservice.erros.NotFoundException;
import com.rafu.libraryservice.services.IBookService;
import com.rafu.libraryservice.vo.requests.BookRequest;
import com.rafu.libraryservice.vo.responses.BookResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {
    private final IBookService service;
    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable final Long id) {
        return service.findById(id).map(b -> ResponseEntity.ok(this.toResponse(b))).orElseThrow(() -> new NotFoundException("Book"));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll() {
        return ResponseEntity.ok().body(
                this.service
                        .findAll()
                        .stream()
                        .map(this::toResponse)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<BookResponse> post(@RequestBody final BookRequest request) {
        final var book = service.create(toBook(request));
        return ResponseEntity.ok().body(this.toResponse(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> put(@PathVariable final Long id, @RequestBody final BookRequest request) {
        final var book = service.update(id, toBook(request));
        return ResponseEntity.ok().body(this.toResponse(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponse> delete(@PathVariable final Long id) {
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
