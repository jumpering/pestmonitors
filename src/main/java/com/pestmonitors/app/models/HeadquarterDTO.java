package com.pestmonitors.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class HeadquarterDTO implements Serializable {
    private final Integer id;

    @NonNull
    private final String name;

    private final String adress;

    private final String city;
}
