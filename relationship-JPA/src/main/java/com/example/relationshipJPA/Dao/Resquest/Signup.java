package com.example.relationshipJPA.Dao.Resquest;




import com.example.relationshipJPA.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Signup {
    private String name;
    private String email;
    private String password;
    private String wing;
    private String flat;
    private long mobile;
    private Role role;
}


