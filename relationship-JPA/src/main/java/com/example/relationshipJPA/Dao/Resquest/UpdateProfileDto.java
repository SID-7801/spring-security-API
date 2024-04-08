package com.example.relationshipJPA.Dao.Resquest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateProfileDto {
    private String name;
    private String email;
    private long mobile;
    private String wing;
    private String flat;
}
