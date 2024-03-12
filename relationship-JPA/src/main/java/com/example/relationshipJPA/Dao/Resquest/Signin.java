package com.example.relationshipJPA.Dao.Resquest;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Signin {

    private String email;
    private String password;
}
