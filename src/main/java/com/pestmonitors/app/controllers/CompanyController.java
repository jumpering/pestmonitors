package com.pestmonitors.app.controllers;

import com.pestmonitors.app.models.CompanyDTO;
import com.pestmonitors.app.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

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

}
