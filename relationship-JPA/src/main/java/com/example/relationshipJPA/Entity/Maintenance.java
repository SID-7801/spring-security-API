package com.example.relationshipJPA.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Month;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Maintenance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mid;

	@Column(nullable = false)
	private double amount;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Month month;

	@Column(nullable = false, name = "due_date")
	private LocalDate dueDate;

	@Column(nullable = false)
	private double penalties;

	@Column(nullable = false, name = "due_amount")
	private double dueAmount;

	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_mem_id")
	private Member member;

}
