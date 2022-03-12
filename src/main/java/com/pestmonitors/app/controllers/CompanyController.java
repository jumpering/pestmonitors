package com.pestmonitors.app.controllers;

import com.pestmonitors.app.models.CompanyDTO;
import com.pestmonitors.app.models.HeadquarterDTO;
import com.pestmonitors.app.models.projections.CompanyBasicDTO;
import com.pestmonitors.app.services.CompanyService;
import com.pestmonitors.app.services.HeadquarterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
@Api(tags = "Company API Rest")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private HeadquarterService headquarterService;

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<CompanyDTO> companiesList;
        companiesList = this.companyService.getAllCompanies();
        return ResponseEntity.ok(companiesList);
    }

    @GetMapping("/companies/basic")
    public List<CompanyBasicDTO> getAllBasic(@RequestParam(required = false)String name){
        List<CompanyBasicDTO> companyBasicDTOList = this.companyService.findAllBasic();
        //requestParam name
        if(name != null){
            companyBasicDTOList = companyBasicDTOList.stream()
                    .filter(element -> element.getName().equals(name))
                    .collect(Collectors.toList());
        }
    return companyBasicDTOList;
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyDTO> getCompanyById(@PathVariable Integer id) {
        Optional<CompanyDTO> optCompany = this.companyService.getCompanyById(id);
        if(optCompany.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        //hateoas
        CompanyDTO companyDTO = optCompany.get();
        Link withSelfRel = linkTo(methodOn(CompanyController.class).getCompanyById(companyDTO.getId())).withSelfRel();
        companyDTO.add(withSelfRel);
        return ResponseEntity.ok(companyDTO);
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