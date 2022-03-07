package com.pestmonitors.app.dao.repositories;

import com.pestmonitors.app.dao.entities.MapEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends JpaRepository<MapEntity, Integer> {

}
