package com.pestmonitors.app.controllers;

import com.pestmonitors.app.models.CompanyDTO;
import com.pestmonitors.app.models.HeadquarterDTO;
import com.pestmonitors.app.services.CompanyService;
import com.pestmonitors.app.services.HeadquarterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
@Api(tags = "Company API Rest")
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

    @GetMapping("/companies")
    public List<CompanyDTO> getAllCompaniesRelations(@RequestParam (value = "relations") boolean relations) {
        List<CompanyDTO> companiesList = this.companyService.getAllCompaniesRelations(relations);
        return companiesList;
    }

    @GetMapping("/companies/{id}")
    public CompanyDTO getCompanyById(@PathVariable Integer id) {
        Optional<CompanyDTO> optCompany = this.companyService.getCompanyById(id);
        return optCompany.orElse(null);
    }

    @GetMapping("/companies/")
    public CompanyDTO getCompanyByName(@RequestParam(value = "name")  String name) {
        Optional<CompanyDTO> optCompany = this.companyService.getCompanyByName(name);
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

    //nested
    @GetMapping("/companies/{id}/headquarters")
    public List<HeadquarterDTO> getCompanyHeadquarters(@PathVariable Integer id){
        return this.headquarterService.getByCompanyId(id);
    }
}
