package com.pestmonitors.app.dao.repositories;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.models.projections.CompanyWithoutRelationsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer>,
        CompanyCustomRepository, //para criteria repository
        JpaSpecificationExecutor<CompanyEntity> { //para specifications

    @Query(value = "select c.id, c.name, c.telf from companies c", nativeQuery = true)
    List<CompanyWithoutRelationsDTO> findAllWithoutRelations();

    boolean existsCompanyEntityByName(String name);

}
