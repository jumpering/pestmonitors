package com.pestmonitors.app.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(unique = true)
    private String name;

    @Column(name = "telf")
    private Integer telf;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "CIF")
    private String CIF;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private List<HeadquarterEntity> headquarters;
}
