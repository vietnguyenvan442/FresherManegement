package com.example.fresher_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fresher")
public class Fresher extends User{

	@JsonIgnore
	@JoinColumn(name = "language_id")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Language language;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fresher", cascade = CascadeType.ALL)
	private List<Course> listCourse;
}
