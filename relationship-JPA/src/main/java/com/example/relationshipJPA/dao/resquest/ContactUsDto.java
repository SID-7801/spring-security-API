package com.example.relationshipJPA.dao.resquest;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactUsDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sr;

    @NotEmpty(message = "Invalid full name")
    @Column(name = "fullname")
    private String fullName;

    @NotEmpty(message = "Email can't be empty")
    @Email(message = "Enter valid email!")
    private String email;

    @NotEmpty(message = "Mobile number can't be empty")
    @Size(min = 10, max = 10, message = "Please enter valid number!")
    private String mobile;

    @NotEmpty(message = "Message can't be empty")
    private String message;
}
