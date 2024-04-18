package com.example.relationshipJPA.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Complain
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long comid;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime complaintDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_mem_id")
    private Member mem_id;

}
