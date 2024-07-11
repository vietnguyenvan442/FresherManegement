package com.example.fresher_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "test")
public class Test  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private Date start_time;
	private int period;

	@JsonIgnore
	@JoinColumn(name = "rank_id")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Rank rank;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "test", cascade = CascadeType.ALL)
	private List<Result> listResult;
}
