package com.pestmonitors.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pestmonitors.app.dao.entities.HeadquarterEntity;
import lombok.*;
//import net.minidev.json.annotate.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor //agregado a posteriori para test
@AllArgsConstructor //agregado para builder y test
@NoArgsConstructor
@JsonPropertyOrder({ "cif","name","description","telf"})
@JsonIgnoreProperties("links")
public class CompanyDTO extends RepresentationModel<CompanyDTO> { //extend para hateoas

    private Integer id;

    @NonNull
    @NotNull
    @NotBlank
    private String name;

    private Integer telf;

    private String description;

    private String CIF;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private List<HeadquarterEntity> headquarters;
}
