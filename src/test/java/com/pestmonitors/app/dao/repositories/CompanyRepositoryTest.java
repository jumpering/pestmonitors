package com.pestmonitors.app.dao.repositories;

import com.pestmonitors.app.dao.entities.CompanyEntity;
import com.pestmonitors.app.models.CompanyDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    private static final ModelMapper modelMapper = new ModelMapper();

    private static CompanyDTO companyDTO;
    private static CompanyEntity expectedCompanyEntity;
    private static Optional<CompanyEntity> optionalCompanyEntity;

    @BeforeAll
    public static void beforeAll(){
        companyDTO = CompanyDTO.builder().id(1).name("xavi").build();
        expectedCompanyEntity = modelMapper.map(companyDTO, CompanyEntity.class);
        optionalCompanyEntity = Optional.of(expectedCompanyEntity);
    }

    @Test
    public void givenNewCompanyWhenCreateCompanyThenReturnSameCompany(){
        this.companyRepository.save(expectedCompanyEntity);
        assertEquals(Optional.of(expectedCompanyEntity), this.companyRepository.findById(1));
    }

    @Test
    public void givenWrongIdWhenCompanyFindFindIdThenReturnVoid(){
        assertEquals(Optional.empty(),this.companyRepository.findById(10));
    }
}
