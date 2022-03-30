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

import javax.swing.text.html.Option;
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

    private List<CompanyDTO> companyDTOList;

    private final ModelMapper modelMapper = new ModelMapper();

    //@BeforeAll  //este me da problemas... ojo ha de ser static o anotación en la clase @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @BeforeEach
    public void beforeEach(){
//        this.companyDTOList = new ArrayList<>();
//        CompanyDTO companyDTO1 = new CompanyDTO().builder().id(1).name("xavi").build();
//        CompanyDTO companyDTO2 = new CompanyDTO().builder().id(2).name("marteta").build();
//        this.companyDTOList.add(companyDTO1);
//        this.companyDTOList.add(companyDTO2);
//        List<CompanyEntity> companyEntityList = new ArrayList<>();
//        for (CompanyDTO companyDTO : companyDTOList ){
//            companyEntityList.add(this.modelMapper.map(companyDTO, CompanyEntity.class));
//        }
//        Mockito.when(this.companyRepository.findAll()).thenReturn(companyEntityList);
    }

    @Test
    public void givenListOfCompaniesWhenCompanyServiceFindAllThenReturnList(){
        this.companyDTOList = new ArrayList<>();
        CompanyDTO companyDTO1 = new CompanyDTO().builder().id(1).name("xavi").build();
        CompanyDTO companyDTO2 = new CompanyDTO().builder().id(2).name("marteta").build();
        this.companyDTOList.add(companyDTO1);
        this.companyDTOList.add(companyDTO2);
        List<CompanyEntity> companyEntityList = new ArrayList<>();
        for (CompanyDTO companyDTO : companyDTOList ){
            companyEntityList.add(this.modelMapper.map(companyDTO, CompanyEntity.class));
        }
        Mockito.when(this.companyRepository.findAll()).thenReturn(companyEntityList);




        List<CompanyDTO> companyDTOListExpected = this.companyDTOList;
        assertEquals(companyDTOListExpected, this.companyService.getAllCompanies());
        assertEquals(2, this.companyService.getAllCompanies().size());
    }

    @Test
    public void givenIdWhenCompanyServiceFindByIdThenReturnOptionalCompanyDTO(){
        CompanyDTO companyDTO = new CompanyDTO().builder().id(1).name("xavi").build();
        CompanyEntity companyEntity = this.modelMapper.map(companyDTO,CompanyEntity.class);
        Mockito.when(this.companyRepository.getById(1)).thenReturn(companyEntity);
        Mockito.when(this.companyRepository.existsById(1)).thenReturn(true);
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
        CompanyDTO companyDTOExpected = new CompanyDTO().builder().name("xavi").build();
        CompanyEntity companyEntity = this.modelMapper.map(companyDTOExpected, CompanyEntity.class);
        Optional<CompanyDTO> optionalCompanyDTO = Optional.of(companyDTOExpected);
        Mockito.when(this.companyRepository.save(companyEntity)).thenReturn(companyEntity);
        assertEquals(optionalCompanyDTO, this.companyService.createCompany(companyDTOExpected));
    }

//    @Test
//    public void givenWrongCompanyDTOWhenCompanyServiceCreateCompanyThenReturnOptionalEmpty(){
//        CompanyDTO companyDTOExpected = new CompanyDTO().builder().name("xavi").build();
//        CompanyEntity companyEntity = this.modelMapper.map(companyDTOExpected, CompanyEntity.class);
//        //Optional<CompanyDTO> optionalCompanyDTO = Optional.of(companyDTOExpected);
//        Mockito.when(this.companyRepository.save(companyEntity)).thenReturn(companyEntity);
//
//
//        Optional<CompanyDTO> optionalCompanyDTO = Optional.empty();
//        assertEquals(optionalCompanyDTO, this.companyService.createCompany(companyDTOExpected));
//    }
}
