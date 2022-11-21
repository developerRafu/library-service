package com.rafu.libraryservice.vo.responses.enums;

import lombok.Getter;

@Getter
public enum TokenType {
  BEARER("Bearer ");
  private final String desc;

  TokenType(final String desc) {
    this.desc = desc;
  }
}
