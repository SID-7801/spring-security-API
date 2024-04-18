package com.example.relationshipJPA.dao.resquest;

import com.example.relationshipJPA.entity.Member;
import com.example.relationshipJPA.entity.Role;
import com.example.relationshipJPA.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequestDto {

    private Long srNo;
    private Role requestedRole;
    private LocalDateTime requestDate;
    private LocalDateTime approvedDate;
    private Status status;
    private Role approvedBy;
    private Member member;

}
