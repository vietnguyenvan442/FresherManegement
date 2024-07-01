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
@Table(name = "area")
public class Area {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "area", cascade = CascadeType.ALL)
	private List<Center> listCenter;
	
	@Override
	public String toString() {
		return name;
	}
}
