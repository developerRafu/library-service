package com.rafu.libraryservice.vo;

import lombok.Getter;

@Getter
public class InvalidHeaderException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String header;

    public InvalidHeaderException(final String header) {
        this.header = header;
    }
}
