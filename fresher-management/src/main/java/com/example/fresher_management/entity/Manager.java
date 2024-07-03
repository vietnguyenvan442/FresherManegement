package com.example.fresher_management.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "manager")
public class Manager extends User{

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Center> listCenter;


    public List<Center> getManagedCenters() {
        return listCenter;
    }

    public void setManagedCenters(List<Center> managedCenters) {
        this.listCenter = managedCenters;
    }
}
