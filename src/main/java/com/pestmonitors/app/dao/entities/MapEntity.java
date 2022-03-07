package com.pestmonitors.app.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","headquarter"})
@Entity( name = "maps")
public class MapEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;

    private String urlFile;

    private boolean active;

    //private List<SensorEntity> sensors; //TODO

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private HeadquarterEntity headquarter;
}
