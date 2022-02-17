package com.pestmonitors.app.dao.repositories;

import com.pestmonitors.app.dao.entities.HeadquarterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeadquarterRepository extends JpaRepository<HeadquarterEntity, Integer> {
    List<HeadquarterEntity> findByNameContaining(String name);
}