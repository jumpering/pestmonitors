package com.pestmonitors.app.dao.repositories;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.dao.entities.HeadquarterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadquarterRepository extends JpaRepository<HeadquarterEntity, Integer> {

    List<HeadquarterEntity> findByCompany(CompanyEntity company);


    List<HeadquarterEntity> findByCompanyId(Integer id);

    boolean existsHeadquarterByName(String name);
}