package com.example.relationshipJPA.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "invalid description")
    private String description;

    @NotEmpty(message = "invalid title")
    private String title;

    @Column(nullable = false)
    private LocalDateTime complaintDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_mem_id")
    private Member mem_id;

}
