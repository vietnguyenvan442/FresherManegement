package com.example.fresher_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "merger")
public class Merger  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date date;

	@JoinColumn(name = "center_first_id")
	@ManyToOne(fetch = FetchType.LAZY) // Change FetchType.EAGER to FetchType.LAZY
	private Center center_first;

	@JoinColumn(name = "center_second_id")
	@ManyToOne(fetch = FetchType.LAZY) // Change FetchType.EAGER to FetchType.LAZY
	private Center center_second;

	@JoinColumn(name = "center_new_id")
	@ManyToOne(fetch = FetchType.LAZY) // Change FetchType.EAGER to FetchType.LAZY
	private Center center_new;
}
