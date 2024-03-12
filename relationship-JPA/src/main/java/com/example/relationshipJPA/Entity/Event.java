package com.example.relationshipJPA.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long funcid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String funcType;

    @Column(nullable = false)
    private Date dateFrom;

    @Column(nullable = false)
    private Date dateTo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_mem_id")
    private Member mem_id;
}
