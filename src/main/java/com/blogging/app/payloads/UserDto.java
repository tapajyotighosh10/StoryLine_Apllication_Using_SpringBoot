package com.blogging.app.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NonNull
    @Size(min = 10, message = "Username must be min of 10 character")
    private String name;

    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Email should match the pattern '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$'")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min =8,max = 10,message = "password must be min of 8 character and max of 10 character.")
    private String password;

    @NotNull(message = "About cannot be null")
    private String about;
}
