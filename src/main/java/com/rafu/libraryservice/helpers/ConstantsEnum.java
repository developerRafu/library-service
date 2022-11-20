package com.rafu.libraryservice.helpers;

import lombok.Getter;

@Getter
public enum ConstantsEnum {
    AUTHORIZATION_HEADER("Authorization"),
    BEARER("Bearer"),
    POST_METHOD("POST"),
    USERS("users"),
    LOGIN("login"),
    SWAGGER_UI("swagger-ui"),
    GET_METHOD("GET");
    private final String value;

    ConstantsEnum(String value) {
        this.value = value;
    }
}
