package com.example.relationshipJPA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long comid;

    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_mem_id")
    private Member mem_id;
}
