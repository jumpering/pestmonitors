package com.pestmonitors.app.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "companies")
public class CompanyEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column(name = "telf")
    private Integer telf;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    //@JsonIgnore
    private List<HeadquarterEntity> headquarters;
}
