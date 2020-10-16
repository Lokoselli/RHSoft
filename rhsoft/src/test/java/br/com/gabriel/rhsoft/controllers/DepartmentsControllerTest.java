package br.com.gabriel.rhsoft.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
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
import br.com.gabriel.rhsoft.builders.DepartmentBuilder;
import br.com.gabriel.rhsoft.conf.AppWebConfiguration;
import br.com.gabriel.rhsoft.conf.DataSourceConfigurationTest;
import br.com.gabriel.rhsoft.conf.JPAConfiguration;
import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.Department;
import br.com.gabriel.rhsoft.testing_daos.CompanyDAOForTesting;
import br.com.gabriel.rhsoft.testing_daos.DepartmentDAOForTesting;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class, CompanyBuilder.class, CompanyDAOForTesting.class, DepartmentDAOForTesting.class})
@ActiveProfiles("test")
public class DepartmentsControllerTest extends ControllerTest{

    @Autowired
    CompanyDAOForTesting companyDAOForTesting;

    @Autowired
    DepartmentDAOForTesting departmentDAOForTesting;

    private Company company;

    private String controllerPath = "departments";

    private CompanyBuilder companyBuilder = new CompanyBuilder();
    private DepartmentBuilder departmentBuilder = new DepartmentBuilder();

    @Before
    public void createCompany(){
        Company company = companyDAOForTesting.persistAndReturnPersisted(companyBuilder.buildWithName("teste").getCompany());

        this.company = company;
    }


    @Test
    public void testDepartmentForm() throws Exception {
        String goTo = "/" + controllerPath + "/form";
        String expectedUrl = webInfPath + controllerPath + "/departmentsForm.jsp";
        

        mockMvc.perform(MockMvcRequestBuilders.get(goTo))
               .andExpect(MockMvcResultMatchers.forwardedUrl(expectedUrl));
    }

    @Test
    public void testDepartmentEditForm() throws Exception {

        Department department = departmentBuilder.buildWithEverything("departmentTest", company, new HashSet<>() ).getDepartment();

        department = departmentDAOForTesting.persistAndReturnPersisted(department);

        String goTo = "/" + controllerPath + "/edit";
        String expectedUrl =  webInfPath + controllerPath + "/departmentsEditForm.jsp";

        mockMvc.perform(MockMvcRequestBuilders.post(goTo)
                                              .param("id", "1")
                                              .sessionAttr("exposedCompany", company))
               .andExpect(MockMvcResultMatchers.forwardedUrl(expectedUrl))
               .andExpect(MockMvcResultMatchers.model().attribute("department", department));

    }

    @Test
    public void testCreateDepartmentWithExposedCompany() throws Exception {

        Department department = departmentBuilder.buildWithEverything("departmentTest", company, new HashSet<>() ).getDepartment();

        String goTo = "/" + controllerPath;
        String expectedUrl = "/" + company.getId();

        mockMvc.perform(MockMvcRequestBuilders.post(goTo)
                                              .flashAttr("department", department)
                                              .sessionAttr("exposedCompany", company))
                .andExpect(MockMvcResultMatchers.redirectedUrl(expectedUrl));

        assertEquals(departmentDAOForTesting.findById(department.getId()), department);
        
    }

    //@Test
    public void testCreateDepartmentWithoutExposed() throws Exception {
        Department department = departmentBuilder.buildWithEverything("departmentTest", new Company(), new HashSet<>() ).getDepartment();

        String goTo = "/" + controllerPath;

        try {
             mockMvc.perform(MockMvcRequestBuilders.post(goTo)
                                              .flashAttr("department", department));
        } catch (Exception e) {
            //assertTrue(e.getMessage().contains("NullPointerException"));
            Department depPersisted = departmentDAOForTesting.findById(department.getId());
            System.out.println(depPersisted);
            System.out.println(department);
            System.out.println(depPersisted.equals(department));
        }
       
    }

    //@Test
    public void testRemoveDepartmentWithExposedCompany() throws Exception {

        Department department = departmentBuilder.buildWithEverything("departmentTest", company, new HashSet<>() ).getDepartment();

        department = departmentDAOForTesting.persistAndReturnPersisted(department);

        String goTo = "/" + controllerPath + "/remove/" + department.getId();
        String expectedUrl = "/" + company.getId();

        mockMvc.perform(MockMvcRequestBuilders.post(goTo)
                                              .param("id", department.getId().toString())
                                              .sessionAttr("exposedCompany", company))
               .andExpect(MockMvcResultMatchers.redirectedUrl(expectedUrl));
        
        assertFalse(departmentDAOForTesting.checkIfDepartmentExists(department));
    }
    
    @Test
    public void testRemoveDepartmentWithoutExposed() throws Exception {

        Department department = departmentBuilder.buildWithEverything("departmentTest", company, new HashSet<>() ).getDepartment();

        department = departmentDAOForTesting.persistAndReturnPersisted(department);

        String goTo = "/" + controllerPath + "/remove/" + department.getId();

        try {
             mockMvc.perform(MockMvcRequestBuilders.post(goTo)
                                              .param("id", "1")
                                              .flashAttr("department", department));
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("NullPointerException"));
            assertTrue(departmentDAOForTesting.checkIfDepartmentExists(department));
        }
       
    }




    
}