package com.example.relationshipJPA.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForgetPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long f_id;

    @Column(nullable = false)
    private int otp;

    @Column(nullable = false)
    private Date expirationTime;

    @ManyToOne
    @JoinColumn(name = "memId")
    private Member member;
}
