package com.example.relationshipJPA.Dao.Resquest;

import com.example.relationshipJPA.Entity.Role;
import jakarta.persistence.*;

public class MemberDto {
    private long id;
    private String name;
    private String email;
    private String wing;
    private String flat;
    private long mobile;
    private Role role;
}
