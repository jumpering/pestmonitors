package com.pestmonitors.app.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "headquarters")
public class HeadquarterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JsonIgnore
    private CompanyEntity company;
}