package com.rafu.libraryservice.erros.helpers;

import lombok.Getter;

@Getter
public enum MessagesEnum {
    BOOK_ALREADY_CREATED("Book '%s' is already saved in database"),
    ENTITY_NOT_FOUND("Wasn't possible to find the %s in database");
    private final String message;

    MessagesEnum(final String message) {
        this.message = message;
    }

    public String getFormattedMessage(final Object... objects) {
        return String.format(message, objects);
    }
}
