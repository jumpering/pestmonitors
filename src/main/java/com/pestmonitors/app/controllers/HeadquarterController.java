package com.pestmonitors.app.controllers;

import com.pestmonitors.app.models.HeadquarterDTO;
import com.pestmonitors.app.services.HeadquarterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "Headquarter API Rest")
public class HeadquarterController {

    @Autowired
    private HeadquarterService headquarterService;

    @GetMapping("/headquarters")
    public List<HeadquarterDTO> getAllHeadquarters() {
        return this.headquarterService.getAll();
    }

    @GetMapping("/headquarters/{id}")
    public HeadquarterDTO getHeadquarterById(@PathVariable Integer id) {
        Optional<HeadquarterDTO> optionalHeadquarterDTO = this.headquarterService.getById(id);
        return optionalHeadquarterDTO.orElse(null);
    }

    @PostMapping("/headquarters")
    public HeadquarterDTO createHeadquarter(@RequestBody HeadquarterDTO headquarterDTO) {
        Optional<HeadquarterDTO> optionalHeadquarterDTO = this.headquarterService.create(headquarterDTO);
        return optionalHeadquarterDTO.orElse(null);
    }


}
