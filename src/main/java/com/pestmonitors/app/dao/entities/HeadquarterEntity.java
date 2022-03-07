package com.pestmonitors.app.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "headquarters")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","company"})
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
    private CompanyEntity company;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "headquarter_id", referencedColumnName = "id")
    private List<MapEntity> maps;
}