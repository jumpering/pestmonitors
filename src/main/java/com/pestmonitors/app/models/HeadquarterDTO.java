package com.pestmonitors.app.models;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class HeadquarterDTO implements Serializable {
    private Integer id;

    @NonNull
    private String name;

    private String address;

    private String city;

    private CompanyEntity company;


}
