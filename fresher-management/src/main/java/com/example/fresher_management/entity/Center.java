package com.example.fresher_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "center")
public class Center implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String address;
    private String description;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String sdt;
    private boolean state;

    @JoinColumn(name = "area_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Area area;

    @JoinColumn(name = "manager_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Manager manager;

    @JsonBackReference(value = "center-course")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "center", cascade = CascadeType.ALL)
    private List<Course> listCourse;

    @JsonBackReference(value = "center-merger-first")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center_first", cascade = CascadeType.ALL)
    private Set<Merger> listMergerFirst;

    @JsonBackReference(value = "center-merger-second")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center_second", cascade = CascadeType.ALL)
    private Set<Merger> listMergerSecond;

    @JsonBackReference(value = "center-merger-new")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "center_new", cascade = CascadeType.ALL)
    private Set<Merger> listMergerNew;
}
