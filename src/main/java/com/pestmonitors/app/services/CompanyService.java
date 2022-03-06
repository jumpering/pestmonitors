package com.pestmonitors.app.services;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.dao.entities.HeadquarterEntity;
import com.pestmonitors.app.dao.repositories.CompanyRepository;
import com.pestmonitors.app.dao.repositories.HeadquarterRepository;
import com.pestmonitors.app.models.CompanyDTO;
import com.pestmonitors.app.models.HeadquarterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

     @Autowired
     private HeadquarterRepository headquarterRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<CompanyDTO> getAllCompanies() {
        List<CompanyEntity> companyEntities = this.companyRepository.findAll();
        //companyEntities.forEach(e -> System.out.println(e.toString())); //TRAZA!
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        companyEntities.forEach(element -> companyDTOS.add(this.modelMapper.map(element, CompanyDTO.class)));
        return companyDTOS;
    }

    public Optional<CompanyDTO> getCompanyById(Integer id) {
        CompanyEntity companyEntity = this.companyRepository.getById(id);
        return Optional.of(this.modelMapper.map(companyEntity, CompanyDTO.class));
    }

    public Optional<CompanyDTO> createCompany(CompanyDTO companyDTO) {
        CompanyEntity companyEntity = this.modelMapper.map(companyDTO, CompanyEntity.class);
        companyEntity = this.companyRepository.save(companyEntity);
        companyDTO = this.modelMapper.map(companyEntity, CompanyDTO.class);
        Optional<CompanyDTO> optCompany =  Optional.of(companyDTO);
        return optCompany;
    }

    public Optional<CompanyEntity> createCompanyV2(CompanyEntity companyEntity) {
        //CompanyEntity companyEntity = this.modelMapper.map(companyDTO, CompanyEntity.class);
        companyEntity = this.companyRepository.save(companyEntity);
        //companyDTO = this.modelMapper.map(companyEntity, CompanyDTO.class);
        Optional<CompanyEntity> optCompany =  Optional.of(companyEntity);
        return optCompany;
    }


//    public CompanyDTO createHeadquarterCompany(Integer companyId, HeadquarterDTO headquarterDTO){
//        CompanyEntity companyEntity = this.companyRepository.getById(companyId);
//        //INSERT headquarter
//
//        HeadquarterEntity headquarterEntity = this.modelMapper.map(headquarterDTO, HeadquarterEntity.class);
//        this.headquarterRepository.save(headquarterEntity);
//        return null;
//    }

    public void deleteCompany(Integer id) {
        this.companyRepository.deleteById(id);
    }
}
