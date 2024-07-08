package com.example.fresher_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "record")
public class Record {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date start_time;
	private Date end_time;

	@JsonIgnore
<<<<<<< HEAD
	@JoinColumn(name = "course_id")
=======
	@JoinColumn(name = "center_id")
>>>>>>> 9f1e8361f333996e63834602545a5a7b55b43b6f
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Course course;

	@JsonIgnore
	@JoinColumn(name = "fresher_id")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Fresher fresher;

	@JsonIgnore
	@JoinColumn(name = "position_id")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Position position;
}
