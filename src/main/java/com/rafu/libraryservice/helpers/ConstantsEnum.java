package com.rafu.libraryservice.helpers;

import lombok.Getter;

@Getter
public enum ConstantsEnum {
    AUTHORIZATION_HEADER("Authorization"),
    BEARER("Bearer"),
    POST_METHOD("POST"),
    USERS("users"),
    LOGIN("login"),
    SWAGGER_UI("swagger"),
    GET_METHOD("GET"),
    API_DOCS("api-docs"),
    TOKEN("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyYWZ1QG1haWwuY29tIiwiZXhwIjoxNjY5MDY3NjEyfQ.YPIGmKRPSELJ-k5jNCDGApWdmvhviwMXH6Dgd4XhhzM-b55sALfBCcMdsM5PBWLhXbvOyaoeZLvVHOoSxaR8Mw"),
    MOCKED_EMAIL("rafu@mail.com"),
    MOCKED_PASSWORD("123456"),
    NAME("rafu"),
    MOCKED_SECRET("supersenha"),
    MOCKED_EXPIRATION("86400000");
    private final String value;

    ConstantsEnum(String value) {
        this.value = value;
    }
}
