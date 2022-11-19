package com.rafu.libraryservice.erros;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String entity;

    public NotFoundException(String entity) {
        this.entity = entity;
    }
}
