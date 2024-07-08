package com.example.fresher_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "result")
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private float point;
	private LocalDateTime start_test;
	private LocalDateTime end_test;

	@JsonIgnore
	@JoinColumn(name = "test_id")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Test test;

	@JoinColumn(name = "fresher_id")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Fresher fresher;
}
