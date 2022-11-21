package com.rafu.libraryservice.models.filters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
public class BaseFilter {
  private final PageRequest request;
  private int page;
  private int size;
  private String sortArg;
  private Direction direction;

  public BaseFilter() {
    page = 0;
    size = 10;
    sortArg = "name";
    direction = Direction.ASC;
    request = PageRequest.of(page, size, Sort.by(direction, sortArg));
  }

  public void setPage(final int page) {
    this.page = Math.max(page, 0);
  }

  public void setSize(final int size) {
    this.size = Math.max(size, 0);
  }
}
