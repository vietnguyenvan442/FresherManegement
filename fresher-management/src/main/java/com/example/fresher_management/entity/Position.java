package com.example.fresher_management.entity;

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
@Table(name = "position")
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "position", cascade = CascadeType.ALL)
	private List<User> listUser;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "position", cascade = CascadeType.ALL)
	private List<Record> listRecord;
	
	@Override
	public String toString() {
		return name;
	}
}
