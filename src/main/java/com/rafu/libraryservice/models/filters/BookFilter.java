package com.rafu.libraryservice.models.filters;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookFilter extends BaseFilter {
  private String name;
  private Long id;
}
