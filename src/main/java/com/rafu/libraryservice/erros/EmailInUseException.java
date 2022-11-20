package com.rafu.libraryservice.erros;

import lombok.Getter;

@Getter
public class EmailInUseException extends RuntimeException {
    private final String email;

    public EmailInUseException(final String email) {
        this.email = email;
    }
}
