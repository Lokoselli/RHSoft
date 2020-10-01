package br.com.gabriel.rhsoft.controllers;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
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

    private String controllerPath = "departments";

    private CompanyBuilder companyBuilder = new CompanyBuilder();
    private DepartmentBuilder departmentBuilder = new DepartmentBuilder();

    private Company company;
    private MockHttpSession session = new MockHttpSession();

    private String jspUrl(String jspName){
        return webInfPath + controllerPath + "/" + jspName + ".jsp";
    }

    @Before
    public void createCompany(){
        this.company = companyDAOForTesting.persistAndReturnPersisted(companyBuilder.buildWithName("teste").getCompany());
        session.setAttribute("exposedCompany", company);
    }

    //#region Tests Related to departmentForm
    @Test
    public void testDepartmentFormCall() throws Exception {
        String goTo = "/" + controllerPath + "/form";
        String expectedUrl = jspUrl("departmentsForm");
        

        mockMvc.perform(MockMvcRequestBuilders.get(goTo).session(session))
			   .andExpect(MockMvcResultMatchers.forwardedUrl(expectedUrl));
			   
		try {
             mockMvc.perform(MockMvcRequestBuilders.post(goTo));
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("NoExposedCompanyException"));
		}
		
    }

    //#endregion

    //#region Tests Related to departmentEditForm
    @Test
    public void testDepartmentEditFormCall() throws Exception {

        Department department = departmentBuilder.buildWithEverything("departmentTest", company, new HashSet<>() ).getDepartment();

        department = departmentDAOForTesting.persistAndReturnPersisted(department);

        String goTo = "/" + controllerPath + "/edit";
        String expectedUrl = jspUrl("departmentsEditForm");

        mockMvc.perform(MockMvcRequestBuilders.post(goTo)
                                              .param("id", "1")
                                              .session(session))
               .andExpect(MockMvcResultMatchers.forwardedUrl(expectedUrl))
			   .andExpect(MockMvcResultMatchers.model().attribute("department", department));
		
		try {
             mockMvc.perform(MockMvcRequestBuilders.post(goTo));
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("NoExposedCompanyException"));
        }

    }
    //#endregion

    //#region Tests Related to createDepartment
    @Test
    public void testCreateDepartmentCall() throws Exception {

        Department department = departmentBuilder.buildWithEverything("departmentTest", company, new HashSet<>() ).getDepartment();

        String goTo = "/" + controllerPath;
        String expectedUrl = "/" + company.getId();

        mockMvc.perform(MockMvcRequestBuilders.post(goTo)
                                              .flashAttr("department", department)
                                              .session(session))
				.andExpect(MockMvcResultMatchers.redirectedUrl(expectedUrl));  
		
		try {
             mockMvc.perform(MockMvcRequestBuilders.post(goTo));
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("NoExposedCompanyException"));
        }
    }

	//#endregion

    //#region Tests Related to removeDepartment
    @Test
    public void testRemoveDepartmentCall() throws Exception {

        Department department = departmentBuilder.buildWithEverything("departmentTest", company, new HashSet<>() ).getDepartment();

        department = departmentDAOForTesting.persistAndReturnPersisted(department);

        String goTo = "/" + controllerPath + "/remove/" + department.getId();
        String expectedUrl = "/" + company.getId();

        mockMvc.perform(MockMvcRequestBuilders.post(goTo)
                                              .param("id", department.getId().toString())
                                              .session(session))
			   .andExpect(MockMvcResultMatchers.redirectedUrl(expectedUrl));
			   
		try {
             mockMvc.perform(MockMvcRequestBuilders.post(goTo));
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("NoExposedCompanyException"));
		}
		
    }

    //#endregion

    //#region Tests Related to editDepartment
    @Test
    public void testDepartmentEditCall() throws Exception{
        Integer depId = 1;

        Department department = departmentBuilder.buildWithEverything("departmentTest", company , new HashSet<>()).getDepartment();
        department.setId(depId);
        

        String goTo = "/" + controllerPath + "/editDepartment";
        String expectedUrl = "/" + company.getId();

        mockMvc.perform(MockMvcRequestBuilders.post(goTo)
                                              .flashAttr("department", department)
                                              .session(session))
			   .andExpect(MockMvcResultMatchers.redirectedUrl(expectedUrl));
			   
		try{
        mockMvc.perform(MockMvcRequestBuilders.post(goTo));
        }catch(Exception e){
            assertTrue(e.getMessage().contains("NoExposedCompanyException"));
		}
		
	}
	
    //#endregion

    //#region Tests Related to detailDepartment
    @Test
    public void testDetailDepartmentCall() throws Exception{

        Integer depId = 1;

        Department department = departmentBuilder.buildWithEverything("departmentTest", company , new HashSet<>()).getDepartment();
        department.setId(depId);
        

        String goTo = "/" + controllerPath + "/detail";
        String expectedUrl = jspUrl("detail");

        mockMvc.perform(MockMvcRequestBuilders.get(goTo)
                                              .param("id", depId.toString())
                                              .session(session))
               .andExpect(MockMvcResultMatchers.forwardedUrl(expectedUrl))
			   .andExpect(MockMvcResultMatchers.model().attribute("department", department));
		
		try{
            mockMvc.perform(MockMvcRequestBuilders.get(goTo));
        }catch(Exception e){
			assertTrue(e.getMessage().contains("NoExposedCompanyException"));
		}

    }
    
    //#endregion
	
	//#region Tests Related to addWorker

	@Test
	public void addWorkerCall() throws Exception {

		Integer depId = 1;

		Department department = new Department();
		department.setId(depId);

		String goTo = "/" + controllerPath + "/addWorker";
		String expectedUrl = "/departments/detail?id=" + depId;

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/addWorker")
                                              .param("selected", "")
                                              .param("departmentId", depId.toString())
											  .session(session))
			   .andExpect(MockMvcResultMatchers.redirectedUrl(expectedUrl));
		
		try{
			mockMvc.perform(MockMvcRequestBuilders.get(goTo));
		}catch(Exception e){
			assertTrue(e.getMessage().contains("NoExposedCompanyException"));
		}
    }
    
    //#endregion
}