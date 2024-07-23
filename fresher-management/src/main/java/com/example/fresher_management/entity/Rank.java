package com.example.fresher_management.entity;

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
@Table(name = "`rank`")
public class Rank  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rank", cascade = CascadeType.ALL)
	private List<Test> listTest;
}
