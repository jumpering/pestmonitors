package com.pestmonitors.app.controllers;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.dao.entities.HeadquarterEntity;
import com.pestmonitors.app.dao.repositories.CompanyRepository;
import com.pestmonitors.app.dao.repositories.HeadquarterRepository;
import com.pestmonitors.app.models.CompanyDTO;
import com.pestmonitors.app.models.HeadquarterDTO;
import com.pestmonitors.app.services.CompanyService;
import com.pestmonitors.app.services.HeadquarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private HeadquarterService headquarterService;

    @GetMapping("/companies")
    public List<CompanyDTO> getAllCompanies() {
        List<CompanyDTO> companiesList = this.companyService.getAllCompanies();
        return companiesList;
    }

    @GetMapping("/companies/{id}")
    public CompanyDTO getCompanyById(@PathVariable Integer id) {
        Optional<CompanyDTO> optCompany = this.companyService.getCompanyById(id);
//        try{
//            CompanyDTO companyDTO = optCompany.orElseThrow(NoSuchElementException::new);
//            return companyDTO;
//        } catch (NoSuchElementException e){
//            return null;
//        }
        return optCompany.orElse(null);
    }

    @PostMapping("/companies")
    public CompanyDTO createCompany(@RequestBody CompanyDTO companyDTO) {
        Optional<CompanyDTO> optCompany = this.companyService.createCompany(companyDTO);
        return optCompany.orElse(null);
    }

    @DeleteMapping("/companies/{id}")
    public void deleteCompany(@PathVariable Integer id){
        this.companyService.deleteCompany(id);
    }

    //recurso anidado: hago get de los headquarters
    @GetMapping("/companies/{id}/headquarters")
    public List<HeadquarterDTO> getCompanyHeadquarters(@PathVariable Integer id){
        return this.headquarterService.getByCompanyId(id);
    }
}
