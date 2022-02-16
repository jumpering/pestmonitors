package com.pestmonitors.app.services;

import com.pestmonitors.app.models.CompanyDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private List<CompanyDTO> mockedList = List.of(new CompanyDTO(1, "Supsa"), new CompanyDTO(2, "Mercadona"));

    public Optional<CompanyDTO> createCompany(CompanyDTO companyDTO) {
        
        this.mockedList.add(companyDTO);
        this.mockedList.add(new CompanyDTO(100,"Dia"));
        Optional<CompanyDTO> optCompany =  Optional.of(companyDTO);
        return optCompany;
    }

    public void deleteCompany(Integer id) {
    }

    public List<CompanyDTO> getAllCompanies() {
        return this.mockedList;
    }

    public Optional<CompanyDTO> getCompanyById(Integer id) {
        Optional<CompanyDTO> optCompany = this.mockedList.stream().filter(element -> element.getId().equals(id)).findFirst();
        return optCompany;
    }
}
