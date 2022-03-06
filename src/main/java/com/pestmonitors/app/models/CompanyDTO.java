package com.pestmonitors.app.models;

import com.pestmonitors.app.dao.entities.HeadquarterEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.ElementCollection;
import java.util.List;

@Data
@NoArgsConstructor
public class CompanyDTO {

    private Integer id;

    @NonNull
    private String name;

    private Integer telf;

    //@ElementCollection //OJO EN TABLA APARTE!!
    private List<HeadquarterEntity> headquarters;
}
