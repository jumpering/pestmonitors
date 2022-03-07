package com.pestmonitors.app.models;

import com.pestmonitors.app.dao.entities.HeadquarterEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class MapDTO {

    private Integer id;

    @NonNull
    private String name;

    private String urlFile;

    private boolean active;

    //private List<SensorEntity> sensors; //TODO

    private HeadquarterEntity headquarter;
}
