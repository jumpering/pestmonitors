package com.pestmonitors.app.services;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.dao.repositories.CompanyRepository;
import com.pestmonitors.app.models.CompanyDTO;
import com.pestmonitors.app.models.projections.CompanyBasicDTO;
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

    private final ModelMapper modelMapper = new ModelMapper();

    public List<CompanyDTO> getAllCompanies() {
        List<CompanyEntity> companyEntities;
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        companyEntities = this.companyRepository.findAll();
        companyEntities.forEach(element -> companyDTOS.add(this.modelMapper.map(element, CompanyDTO.class)));
        return companyDTOS;
    }

    public List<CompanyBasicDTO> findAllBasic() {
        List<CompanyBasicDTO> companyBasicDTOS;
        companyBasicDTOS = this.companyRepository.findAllBasic();
        return companyBasicDTOS;
    }

    public Optional<CompanyDTO> getCompanyById(Integer id) {
        Optional<CompanyDTO> optionalCompanyDTO = Optional.empty();
        if (this.companyRepository.existsById(id)){
            CompanyEntity companyEntity = this.companyRepository.getById(id);
            CompanyDTO companyDTO = this.modelMapper.map(companyEntity, CompanyDTO.class);
            optionalCompanyDTO = Optional.of(companyDTO);
        }
        return optionalCompanyDTO;
    }

    public Optional<CompanyDTO> createCompany(CompanyDTO companyDTO) {
        CompanyEntity companyEntity = this.modelMapper.map(companyDTO, CompanyEntity.class);
        companyEntity = this.companyRepository.save(companyEntity);
        companyDTO = this.modelMapper.map(companyEntity, CompanyDTO.class);
        return Optional.of(companyDTO);
    }

    public void deleteCompany(Integer id) {
        this.companyRepository.deleteById(id);
    }
}
