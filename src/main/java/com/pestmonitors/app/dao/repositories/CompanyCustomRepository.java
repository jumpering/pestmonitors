package com.pestmonitors.app.dao.repositories;

import com.pestmonitors.app.dao.entities.CompanyEntity;

import java.util.List;

public interface CompanyCustomRepository {

    List<CompanyEntity> findByNameAndTelf(String name, Integer telf);
}
