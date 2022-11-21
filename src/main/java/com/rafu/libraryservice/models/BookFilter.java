package com.rafu.libraryservice.models;

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
