package com.example.fresher_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "manager")
public class Manager extends User{

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Center> listCenter;
}
