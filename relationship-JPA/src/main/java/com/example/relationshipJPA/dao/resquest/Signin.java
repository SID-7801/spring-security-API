package com.example.relationshipJPA.dao.resquest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Signin {
    @Email(message = "Invalid email")
    @NotEmpty
    private String email;
    @NotEmpty(message = "Invalid password")
    private String password;
}
