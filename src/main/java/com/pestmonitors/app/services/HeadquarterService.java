package com.pestmonitors.app.services;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.dao.entities.HeadquarterEntity;
import com.pestmonitors.app.dao.repositories.CompanyRepository;
import com.pestmonitors.app.dao.repositories.HeadquarterRepository;
import com.pestmonitors.app.models.HeadquarterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HeadquarterService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private HeadquarterRepository headquarterRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public List<HeadquarterDTO> getAll(){
        List<HeadquarterEntity> headquarterEntities = this.headquarterRepository.findAll();
        List<HeadquarterDTO> headquarterDTOS = new ArrayList<>();
        headquarterEntities.forEach(element -> headquarterDTOS.add(this.modelMapper.map(element, HeadquarterDTO.class)));
        return headquarterDTOS;
    }

    public Optional<HeadquarterDTO> getById(Integer id){
        HeadquarterEntity headquarterEntity = this.headquarterRepository.getById(id);
        HeadquarterDTO headquarterDTO = this.modelMapper.map(headquarterEntity, HeadquarterDTO.class);
        return Optional.of(headquarterDTO);
    }

    public List<HeadquarterDTO> getByCompanyId(Integer id){
        CompanyEntity companyEntity = this.companyRepository.getById(id);
        List<HeadquarterEntity> headquarterEntities = this.headquarterRepository.findByCompany(companyEntity);
        List<HeadquarterDTO> headquarterDTOS = new ArrayList<>();
        headquarterEntities.forEach(element -> headquarterDTOS.add(this.modelMapper.map(element, HeadquarterDTO.class)));
        return headquarterDTOS;
    }


    public Optional<HeadquarterDTO> create(HeadquarterDTO headquarterDTO) {
        HeadquarterEntity headquarterEntity = this.modelMapper.map(headquarterDTO, HeadquarterEntity.class);
        headquarterEntity = this.headquarterRepository.save(headquarterEntity);
        headquarterDTO = this.modelMapper.map(headquarterEntity, HeadquarterDTO.class);
        return Optional.ofNullable(headquarterDTO);
    }
}
