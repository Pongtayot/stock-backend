package com.pongtayot.restapi_hibernet.controllers.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@NoArgsConstructor
public class UserRequest {
    @NotEmpty
    @Length(min = 1, max = 100)
    private String username;
    @NotEmpty
    @Length(min = 8, message = "The field must be at least {min} characters")
    private String password;
    @NotEmpty
    private String role;
}
