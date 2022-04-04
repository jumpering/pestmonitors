package com.pestmonitors.app.services;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.dao.repositories.CompanyRepository;
import com.pestmonitors.app.models.CompanyDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    private static List<CompanyDTO> companyDTOList;

    private static List<CompanyEntity> companyEntityList;

    private static final ModelMapper modelMapper = new ModelMapper();

    @BeforeAll
    public static void beforeAll(){
        companyDTOList = new ArrayList<>();
        CompanyDTO companyDTO1 = CompanyDTO.builder().id(1).name("xavi").build();
        CompanyDTO companyDTO2 = CompanyDTO.builder().id(2).name("marteta").build();
        companyDTOList.add(companyDTO1);
        companyDTOList.add(companyDTO2);
        companyEntityList = new ArrayList<>();
        for (CompanyDTO companyDTO : companyDTOList ){
            companyEntityList.add(modelMapper.map(companyDTO, CompanyEntity.class));
        }
        //Mockito.when(this.companyRepository.findAll()).thenReturn(companyEntityList);
    }

    @Test
    public void givenListOfCompaniesWhenCompanyServiceFindAllThenReturnList(){
        Mockito.when(this.companyRepository.findAll()).thenReturn(companyEntityList);

        List<CompanyDTO> companyDTOListExpected = companyDTOList;
        assertEquals(companyDTOListExpected, this.companyService.findAllCompanies());
        assertEquals(2, this.companyService.findAllCompanies().size());
    }

    @Test
    public void givenIdWhenCompanyServiceFindByIdThenReturnOptionalCompanyDTO(){
        CompanyDTO companyDTO = CompanyDTO.builder().id(1).name("xavi").build();
        CompanyEntity companyEntity = modelMapper.map(companyDTO,CompanyEntity.class);
        Mockito.when(companyRepository.getById(1)).thenReturn(companyEntity);
        Mockito.when(companyRepository.existsById(1)).thenReturn(true);
        Optional<CompanyDTO> optionalCompanyDTOExpected = Optional.of(companyDTO);
        assertEquals(optionalCompanyDTOExpected, this.companyService.getCompanyById(1));
    }

    @Test
    public void givenWrongIdWhenCompanyServiceFindByIdThenReturnOptionalEmpty(){
        Optional<CompanyDTO> optionalCompanyDTOExpected = Optional.empty();
        assertEquals(optionalCompanyDTOExpected, this.companyService.getCompanyById(2));
    }

    @Test
    public void givenCompanyDTOWhenCompanyServiceCreateCompanyThenReturnOptionalCompanyCreated(){
        CompanyDTO companyDTOExpected = CompanyDTO.builder().name("xavi").build();
        CompanyEntity companyEntity = modelMapper.map(companyDTOExpected, CompanyEntity.class);
        Optional<CompanyDTO> optionalCompanyDTO = Optional.of(companyDTOExpected);
        Mockito.when(companyRepository.save(companyEntity)).thenReturn(companyEntity);
        assertEquals(optionalCompanyDTO, this.companyService.createCompany(companyDTOExpected));
    }

}
