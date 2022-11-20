package com.rafu.libraryservice.erros.helpers;

import lombok.Getter;

@Getter
public enum InternalErrorsEnum {
    INTERNAL_ERROR("Exception of %s");
    private final String message;

    InternalErrorsEnum(final String message) {
        this.message = message;
    }

    public String getFormattedMessage(final Object... objects) {
        return String.format(message, objects);
    }
}
