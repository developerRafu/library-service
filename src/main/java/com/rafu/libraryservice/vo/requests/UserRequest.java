package com.rafu.libraryservice.vo.requests;

import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
  @NotNull private String name;
  @NotNull private String email;
  @NotNull private String password;
}
