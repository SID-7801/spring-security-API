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

    private String name;
    private String email;
    @Size(min = 10,max = 10)
    private String mobile;
    private String wing;
    private String flat;
    private MultipartFile photo;
}
