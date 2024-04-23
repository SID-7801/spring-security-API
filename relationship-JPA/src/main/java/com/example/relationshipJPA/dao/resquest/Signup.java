package com.example.relationshipJPA.dao.resquest;

import com.example.relationshipJPA.entity.Role;
import jakarta.mail.Multipart;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Signup {
    @NotEmpty
    private String name;
    @NotEmpty
    @Email(message = "enter valid email!")
    private String email;
    @NotEmpty(message = "password can't be empty or null")
    @Size(min = 6, message = "enter at least 6 length of password")
    private String password;
    @NotEmpty(message = "not valid wing")
    private String wing;
    @NotEmpty(message = "not valid flat")
    private String flat;
    @NotEmpty
    @Size(min = 10, max = 10, message = "Please enter valid number!")
    private String mobile;
    @NotNull(message = "not valid role")
    private Role role;
    private MultipartFile photo;
}


