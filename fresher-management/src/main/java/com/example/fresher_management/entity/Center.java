package com.example.fresher_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "center")
public class Center {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String address;
	private String description;
	private String email;
	private String name;
	private String sdt;
	private boolean state;

	@JoinColumn(name = "area_id")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Area area;

	@JoinColumn(name = "manager_id")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Manager manager;

	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "center", cascade = CascadeType.ALL)
	private List<Course> listCourse;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "center_first", cascade = CascadeType.ALL)
	private Set<Merger> listMergerFirst; // Change from List<> to Set<>

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "center_second", cascade = CascadeType.ALL)
	private Set<Merger> listMergerSecond; // Change from List<> to Set<>

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "center_new", cascade = CascadeType.ALL)
	private Set<Merger> listMergerNew; // Change from List<> to Set<>
}
