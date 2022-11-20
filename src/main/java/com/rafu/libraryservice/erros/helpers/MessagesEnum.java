package com.rafu.libraryservice.erros.helpers;

import lombok.Getter;

@Getter
public enum MessagesEnum {
    BOOK_ALREADY_CREATED("Book '%s' is already saved in database"),
    ENTITY_NOT_FOUND("Wasn't possible to find the %s in database"),
    EMAIL_ALREADY_SAVED("Email '%s' is already in use"),
    NOT_AUTHORIZED("You are not authorized to access this resource"),
    WRONG_PASSWORD("The password is wrong"),
    INVALID_HEADER("The authorization header is missing or invalid"),
    INTERNAL_SERVER_ERROR("Internal server error");
    private final String message;

    MessagesEnum(final String message) {
        this.message = message;
    }

    public String getFormattedMessage(final Object... objects) {
        return String.format(message, objects);
}
}
