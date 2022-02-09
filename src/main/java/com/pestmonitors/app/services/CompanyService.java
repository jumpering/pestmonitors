package com.pestmonitors.app.services;

import com.pestmonitors.app.models.CompanyDTO;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CompanyService {

    private List<CompanyDTO> mockedList = List.of(new CompanyDTO(1, "Supsa"), new CompanyDTO(2, "Mercadona"));

    public void createCompany(CompanyDTO companyDTO) {
    }

    public void deleteCompany(Integer id) {
    }

    public List<CompanyDTO> getAllCompanies() {
        return this.mockedList;
    }

    public CompanyDTO getCompanyById(Integer id) {
        Optional<CompanyDTO> optCompany = this.mockedList.stream().filter(element -> element.getId().equals(id)).findFirst();
        try{
            CompanyDTO companyDTO = optCompany.orElseThrow(NoSuchElementException::new);
            return companyDTO;
        } catch (NoSuchElementException e){
            return null;
        }
    }
}
