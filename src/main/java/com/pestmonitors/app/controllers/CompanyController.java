package com.pestmonitors.app.controllers;

import com.pestmonitors.app.models.CompanyDTO;
import com.pestmonitors.app.models.HeadquarterDTO;
import com.pestmonitors.app.services.CompanyService;
import com.pestmonitors.app.services.HeadquarterService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
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

    @GetMapping("/companies") //not sense requestParam name, because name is unique on bd...
    public ResponseEntity<List<CompanyDTO>> getAllCompanies(@RequestParam(required = false)String name){
        List<CompanyDTO> companyDTOList = this.companyService.getAllCompanies();

        for (CompanyDTO companyDTO : companyDTOList) {//hateoas
            Link withSelfRel = linkTo(methodOn(CompanyController.class).getCompanyById(companyDTO.getId())).withSelfRel();
            companyDTO.add(withSelfRel);
//            Link headquartersRel = linkTo(methodOn(CompanyController.class).getCompanyById(companyDTO.getId()))
//                    .withRel("headquarters");
//            companyDTO.add(headquartersRel);
        }
        if(name != null){
            if (!this.companyService.existCompanyByName(name)){
                return ResponseEntity.notFound().build();
            }
            companyDTOList = companyDTOList.stream()
                    .filter(element -> element.getName().equals(name))
                    .collect(Collectors.toList());
        }
    return ResponseEntity.ok(companyDTOList);
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
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        Optional<CompanyDTO> optCompany = this.companyService.createCompany(companyDTO);
        if (optCompany.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        companyDTO = optCompany.get();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(companyDTO.getId())
                .toUri(); //resource in header
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/companies/{id}")
    @ApiOperation(notes = "Delete one company by id", value = "Get company by id")//@Api... para swagger
    @ApiResponses(value = { @ApiResponse(code = 200, message = "200 Response ok if the operation was successful"),
                            @ApiResponse(code = 404, message = "404 Resource could not be found") })
    public ResponseEntity<CompanyDTO> deleteCompany(
                                                    @ApiParam(example = "1", value = "Identifier for company", required = true)
                                                    @PathVariable Integer id){
        CompanyDTO companyDTO = this.companyService.deleteCompany(id);
        if (companyDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(companyDTO);
    }

    @PutMapping("companies/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@Valid @RequestBody CompanyDTO companyDTO, @PathVariable Integer id){
        Optional<CompanyDTO> optionalCompanyDTO = this.companyService.updateCompany(companyDTO, id);
        if (companyDTO == null ){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalCompanyDTO.get());
    }

    @GetMapping("/companies/{id}/headquarters") //nested
    public List<HeadquarterDTO> getCompanyHeadquarters(@PathVariable Integer id){
        return this.headquarterService.getByCompanyId(id);
    }
    //¿faltaria recurso query para filtrar todos los campos?
}