package com.pestmonitors.app.models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CompanyDTO {

    @NonNull
    private Integer id;

    @NonNull
    private String name;

    private Integer telf;
}
