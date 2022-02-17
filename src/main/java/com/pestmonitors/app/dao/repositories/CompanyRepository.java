package com.pestmonitors.app.dao.repositories;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.dao.entities.HeadquarterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

    List<CompanyEntity> findByHeadquartersIs(HeadquarterEntity headquarters);
    public CompanyEntity getByNameContains(String contains);
}
