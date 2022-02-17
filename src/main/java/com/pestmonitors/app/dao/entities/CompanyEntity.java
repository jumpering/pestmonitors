package com.pestmonitors.app.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "companies")
public class CompanyEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private Integer telf;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "company_entity_id")
    private List<Headquarter> headquarters = new ArrayList<>();

    public List<Headquarter> getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(List<Headquarter> headquarters) {
        this.headquarters = headquarters;
    }
}
