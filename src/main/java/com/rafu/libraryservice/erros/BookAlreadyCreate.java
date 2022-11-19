package com.rafu.libraryservice.erros;

import lombok.Getter;

@Getter
public class BookAlreadyCreate extends RuntimeException {
    private final String name;

    public BookAlreadyCreate(final String name) {
        this.name = name;
    }
}
