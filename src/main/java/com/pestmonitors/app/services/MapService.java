package com.pestmonitors.app.services;

import com.pestmonitors.app.dao.entities.MapEntity;
import com.pestmonitors.app.dao.repositories.MapRepository;
import com.pestmonitors.app.models.MapDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MapService {

    @Autowired
    private MapRepository mapRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<MapDTO> getAll(){
        List<MapEntity> mapsEntity = this.mapRepository.findAll();
        List<MapDTO> mapsDTO = new ArrayList<>();
        mapsEntity.forEach(element -> mapsDTO.add(this.modelMapper.map(element, MapDTO.class)));
        return mapsDTO;
    }

    public Optional<MapDTO> create(MapDTO map) {
        MapEntity mapEntity = this.modelMapper.map(map, MapEntity.class);
        mapEntity = this.mapRepository.save(mapEntity);
        MapDTO mapDTO = this.modelMapper.map(mapEntity, MapDTO.class);
        return Optional.of(mapDTO);
    }
}
