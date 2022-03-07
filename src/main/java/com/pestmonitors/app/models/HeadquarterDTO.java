package com.pestmonitors.app.models;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.dao.entities.MapEntity;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class HeadquarterDTO {

    private Integer id;

    @NonNull
    private String name;

    private String address;

    private String city;

    private CompanyEntity company;

    private List<MapEntity> maps;
}
