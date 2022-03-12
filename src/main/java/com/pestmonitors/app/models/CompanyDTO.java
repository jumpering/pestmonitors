package com.pestmonitors.app.models;

import com.pestmonitors.app.dao.entities.HeadquarterEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@NoArgsConstructor
public class CompanyDTO extends RepresentationModel<CompanyDTO> { //extennd para hateoas

    private Integer id;

    @NonNull
    private String name;

    private Integer telf;

    private List<HeadquarterEntity> headquarters;
}
