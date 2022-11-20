package com.rafu.libraryservice.vo.requests;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
