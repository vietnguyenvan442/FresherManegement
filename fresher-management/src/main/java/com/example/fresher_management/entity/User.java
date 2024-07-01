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
@Table(name = "`user`")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String name;
	private String dob;
	private String cccd;
	private String sdt;
	private String address;
	private String email;
	private float salary;
	private boolean state;
	
	@JoinColumn(name = "position_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Position position;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private List<Record> listRecord;

}
