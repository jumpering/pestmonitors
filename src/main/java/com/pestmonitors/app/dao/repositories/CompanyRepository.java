package com.pestmonitors.app.dao.repositories;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer> {

    @Query("select c.id, c.name, c.telf from companies c")
    List<CompanyEntity> findAllWithoutRelations();

    CompanyEntity findByName(String name);
}
