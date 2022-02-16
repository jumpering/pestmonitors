package com.pestmonitors.app.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity(name="headquarter")
public class HeadquarterEntity {

    @Id
    private Integer id;
    private String name;
    private String City;
    private String adress;
}
