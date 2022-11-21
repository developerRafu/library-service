package com.rafu.libraryservice.helpers;

import com.rafu.libraryservice.models.domain.User;

public class UserHelper {
  public static User getUser() {
    return User.builder()
        .email(ConstantsEnum.MOCKED_EMAIL.getValue())
        .password(ConstantsEnum.MOCKED_PASSWORD.getValue())
        .id(1L)
        .name(ConstantsEnum.NAME.getValue())
        .build();
  }
}
