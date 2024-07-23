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

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;
	private String dob;

	@Column(nullable = false)
	private String cccd;

	@Column(nullable = false)
	private String sdt;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String email;
	private float salary;
	private boolean state;

	@JoinColumn(name = "role_id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Role role;
}
