package com.pestmonitors.app.controllers;

import com.pestmonitors.app.models.CompanyDTO;
import com.pestmonitors.app.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return this.companyService.getCompanyById(id);
    }

    @PostMapping("/companies")
    public CompanyDTO createCompany(@RequestBody CompanyDTO companyDTO) {
        this.companyService.createCompany(companyDTO);
        return companyDTO;
    }

    @DeleteMapping("/companies/{id}")
    public void deleteCompany(@PathVariable Integer id){
        this.companyService.deleteCompany(id);
    }

}
