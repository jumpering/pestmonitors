package com.pestmonitors.app.services;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.dao.repositories.CompanyRepository;
import com.pestmonitors.app.models.CompanyDTO;
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

    public void deleteCompany(Integer id) {
    }
}
