package com.example.relationshipJPA.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_mem_id")
	private Member member;

}
