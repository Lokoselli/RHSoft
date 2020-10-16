package br.com.gabriel.rhsoft.controllers;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.gabriel.rhsoft.builders.CompanyBuilder;
import br.com.gabriel.rhsoft.conf.AppWebConfiguration;
import br.com.gabriel.rhsoft.conf.DataSourceConfigurationTest;
import br.com.gabriel.rhsoft.conf.JPAConfiguration;
import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.ExposedCompany;
import br.com.gabriel.rhsoft.testing_daos.CompanyDAOForTesting;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class, CompanyBuilder.class, CompanyDAOForTesting.class})
@ActiveProfiles("test")
public class CompanyControllersTest extends ControllerTest {

    @Autowired
    CompanyDAOForTesting companyDAOForTesting;

    @Autowired
    ExposedCompany exposedCompany;

    private CompanyBuilder companyBuilder = new CompanyBuilder();

    @After
    public void cleanUp(){
        this.companyBuilder = new CompanyBuilder();
    }
    
    @Test
    public void testCompanyForm() throws Exception {
        String controllerPath = "/create12345";
        String expectedUrl = webInfPath + "company/companyForm.jsp";

        mockMvc.perform(MockMvcRequestBuilders.get(controllerPath))
                .andExpect(MockMvcResultMatchers.forwardedUrl(expectedUrl));
    }

    @Test
    public void testCreateEmpresa() throws Exception {
        String controllerPath = "/";
        String expectedUrl = "/";

        Company first = companyBuilder.buildWithName("teste").build();

        mockMvc.perform(MockMvcRequestBuilders.post(controllerPath)
                                              .flashAttr("company", first))
                .andExpect(MockMvcResultMatchers.redirectedUrl(expectedUrl));
    }

    @Test
    public void testSetExposedCompany() throws Exception {
        Company company =  companyDAOForTesting.persistAndReturnPersisted(companyBuilder.buildWithName("teste").build());

        String controllerPath = "/" + company.getId();
        String expectedUrl = webInfPath + "home.jsp";

        mockMvc.perform(MockMvcRequestBuilders.get(controllerPath))
               .andExpect(MockMvcResultMatchers.forwardedUrl(expectedUrl))
               .andExpect(MockMvcResultMatchers.request().sessionAttribute("exposedCompany", company));
    }

    
}