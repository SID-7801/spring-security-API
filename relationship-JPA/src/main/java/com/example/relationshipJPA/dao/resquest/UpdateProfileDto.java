package com.example.relationshipJPA.dao.resquest;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateProfileDto {

    @NotEmpty
    private String name;
    private String email;
    @NotEmpty
    @Size(min = 10,max = 10)
    private String mobile;
    @NotEmpty
    private String wing;
    @NotEmpty
    private String flat;
    private MultipartFile photo;
}
