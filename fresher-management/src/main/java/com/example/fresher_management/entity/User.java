package com.example.fresher_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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

	@JsonIgnore
	@JoinColumn(name = "role_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
}
