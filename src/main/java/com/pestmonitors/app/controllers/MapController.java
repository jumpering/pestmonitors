package com.pestmonitors.app.controllers;

import com.pestmonitors.app.dao.entities.MapEntity;
import com.pestmonitors.app.models.MapDTO;
import com.pestmonitors.app.services.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping("/maps")
    public List<MapDTO> getAllMaps(){
       return this.mapService.getAll();
    }

    @PostMapping("/maps")
    public MapDTO create(@RequestBody MapDTO map){
        this.mapService.create(map);
        return null;
    }
}
