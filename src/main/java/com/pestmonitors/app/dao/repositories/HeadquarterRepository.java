package com.pestmonitors.app.dao.repositories;

import com.pestmonitors.app.dao.entities.Headquarter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeadquarterRepository extends CrudRepository<Headquarter, Integer> {
    List<Headquarter> findByNameContaining(String name);
}