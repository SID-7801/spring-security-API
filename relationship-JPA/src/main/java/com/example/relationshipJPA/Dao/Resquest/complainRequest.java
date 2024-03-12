package com.example.relationshipJPA.Dao.Resquest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class complainRequest {
    private String description;
    private String status;

}
