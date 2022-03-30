package com.pestmonitors.app.controllers;

import com.pestmonitors.app.models.CompanyDTO;
import com.pestmonitors.app.services.CompanyService;
import com.pestmonitors.app.services.HeadquarterService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @MockBean
    private CompanyService companyService;

    @MockBean
    private HeadquarterService headquarterService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenCompaniesWhenCompanyFindAllThenReturnAllCompanies() throws Exception {
        List<CompanyDTO> companyDTOList = new ArrayList<>();
        CompanyDTO companyDTO1 = new CompanyDTO().builder().id(1).name("xavi").build();
        CompanyDTO companyDTO2 = new CompanyDTO().builder().id(2).name("marteta").build();
        companyDTOList.add(companyDTO1);
        companyDTOList.add(companyDTO2);
        Mockito.when(this.companyService.getAllCompanies()).thenReturn(companyDTOList);
        Mockito.when(this.companyService.existCompanyByName(null)).thenReturn(false);
        this.mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name", Matchers.is("marteta")));
    }
}
