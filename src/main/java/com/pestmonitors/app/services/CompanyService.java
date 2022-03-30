package com.pestmonitors.app.services;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.dao.repositories.CompanyRepository;
import com.pestmonitors.app.dao.specifications.CompanySpecification;
import com.pestmonitors.app.models.CompanyDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<CompanyDTO> findAllCompanies() {
        List<CompanyEntity> companyEntities;
        List<CompanyDTO> companyDTOS = new ArrayList<>();
        companyEntities = this.companyRepository.findAll();
        companyEntities.forEach(element -> companyDTOS.add(this.modelMapper.map(element, CompanyDTO.class)));
        return companyDTOS;
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

    public boolean existCompanyByName(String name){
        return this.companyRepository.existsCompanyEntityByName(name);
    }

    public Optional<CompanyDTO> createCompany(CompanyDTO companyDTO) {
        Optional<CompanyDTO> optionalCompanyDTO = Optional.empty();
        if (!this.companyRepository.existsCompanyEntityByName(companyDTO.getName())){
            CompanyEntity companyEntity = this.modelMapper.map(companyDTO, CompanyEntity.class);
            companyEntity = this.companyRepository.save(companyEntity);
            companyDTO = this.modelMapper.map(companyEntity, CompanyDTO.class);
            optionalCompanyDTO = Optional.of(companyDTO);
        }
        return optionalCompanyDTO;
    }

    public CompanyDTO deleteCompany(Integer id) {
        if (!this.companyRepository.existsById(id)){
            return null;
        }
        CompanyEntity companyEntity = this.companyRepository.getById(id);
        CompanyDTO companyDTO = this.modelMapper.map(companyEntity, CompanyDTO.class);
        this.companyRepository.deleteById(id);
        return companyDTO;
    }

    public Optional<CompanyDTO> updateCompany(CompanyDTO companyDTO, Integer id) {
        if (!this.companyRepository.existsById(id)){
            return Optional.empty();
        }
        CompanyDTO finalCompanyDTO = companyDTO;
        CompanyEntity companyEntity = this.companyRepository.findById(id)
                .map(company -> {
                    company.setName(finalCompanyDTO.getName());
                    company.setTelf(finalCompanyDTO.getTelf());
                    company.setDescription(finalCompanyDTO.getDescription());
                    company.setCIF(finalCompanyDTO.getCIF());
                    return this.companyRepository.save(company);
                }).get();
        companyDTO = this.modelMapper.map(companyEntity, CompanyDTO.class);
        return Optional.of(companyDTO);
    }

    //with criteria on repository
//    public ResponseEntity<List<CompanyDTO>> findCompanyByNameAndTelf(String name, Integer telf) {
//        List<CompanyEntity> companyEntities = this.companyRepository.findByNameAndTelf(name, telf);
//        List<CompanyDTO> companyDTOS = companyEntities.stream().map(e -> this.modelMapper.map(e, CompanyDTO.class)).collect(Collectors.toList());
//
//        return ResponseEntity.ok(companyDTOS);
//    }

    //with specification on repository
    public ResponseEntity<List<CompanyDTO>> findCompanyByNameAndTelf(String name, Integer telf) {
        Specification<CompanyEntity> specification = Specification.where(CompanySpecification.hasName(name)
                                                    .and(CompanySpecification.hasTelf(telf)));

        List<CompanyEntity> companyEntities = this.companyRepository.findAll(specification);
        List<CompanyDTO> companyDTOS = companyEntities.stream().map(e -> this.modelMapper.map(e, CompanyDTO.class)).collect(Collectors.toList());

        return ResponseEntity.ok(companyDTOS);
    }

}
