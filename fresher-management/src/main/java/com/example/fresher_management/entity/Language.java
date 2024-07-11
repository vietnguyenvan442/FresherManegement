package com.example.fresher_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "language")
public class Language  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;

	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "language", cascade = CascadeType.ALL)
	private List<Fresher> listFresher;
	
	@Override
	public String toString() {
		return name;
	}
}
