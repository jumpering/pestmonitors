package com.pestmonitors.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.dao.entities.MapEntity;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@JsonPropertyOrder({ "name","address","city","maps"})
@JsonIgnoreProperties("links")
public class HeadquarterDTO {

    private Integer id;

    @NonNull
    @NotNull
    @NotBlank
    private String name;

    private String address;

    private String city;

    @JsonIgnore
    private CompanyEntity company;

    private List<MapEntity> maps;
}
