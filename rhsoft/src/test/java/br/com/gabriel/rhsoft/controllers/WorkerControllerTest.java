package br.com.gabriel.rhsoft.controllers;

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
import br.com.gabriel.rhsoft.builders.WorkerBuilder;
import br.com.gabriel.rhsoft.conf.AppWebConfiguration;
import br.com.gabriel.rhsoft.conf.DataSourceConfigurationTest;
import br.com.gabriel.rhsoft.conf.JPAConfiguration;
import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.Department;
import br.com.gabriel.rhsoft.models.Worker;
import br.com.gabriel.rhsoft.testing_daos.CompanyDAOForTesting;
import br.com.gabriel.rhsoft.testing_daos.DepartmentDAOForTesting;
import br.com.gabriel.rhsoft.testing_daos.WorkerDAOForTesting;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class, CompanyBuilder.class, CompanyDAOForTesting.class, DepartmentDAOForTesting.class, WorkerDAOForTesting.class})
@ActiveProfiles("test")
public class WorkerControllerTest extends ControllerTest {

    @Autowired
    private CompanyDAOForTesting companyDAOForTesting;

    @Autowired
    private WorkerDAOForTesting workerDAOForTesting;

    @Autowired
    private DepartmentDAOForTesting departmentDAOForTesting;

    CompanyBuilder companyBuilder = new CompanyBuilder();
    DepartmentBuilder departmentBuilder = new DepartmentBuilder();
    WorkerBuilder workerBuilder = new WorkerBuilder();

    private MockHttpSession session;

    private Company company;
    private Department department;

    @Before
    public void startUp(){

        super.controllerPath = "workers";

        this.workerBuilder = new WorkerBuilder();
        this.departmentBuilder = new DepartmentBuilder();
        this.companyBuilder = new CompanyBuilder();

        this.company = companyDAOForTesting.persistAndReturnPersisted(companyBuilder.newCompany().build());
        this.department = departmentDAOForTesting.persistAndReturnPersisted(departmentBuilder.buildBasic(this.company).build());


        this.session = new MockHttpSession();
        session.setAttribute("exposedCompany", company);

        
    }

    //#region workerForm
    @Test
    public void workerFormCall() throws Exception{

        String goTo = "/" + controllerPath + "/form";
        String expectedUrl = jspUrl("workerForm");

        mockMvc.perform(MockMvcRequestBuilders.get(goTo))
               .andExpect(MockMvcResultMatchers.forwardedUrl(expectedUrl));

    }

    //#endregion

    //#region workerEditForm
    @Test
    public void workerEditFormCall() throws Exception{

        Worker worker = workerBuilder.createWithEverything(company.getId()).build();
        worker = workerDAOForTesting.persistAndReturnPersisted(worker);

        String goTo = "/" + controllerPath + "/edit";
        String expectedUrl = jspUrl("workerEditForm");

        String previousPage = ",/rhsoft/1";

        mockMvc.perform(MockMvcRequestBuilders.get(goTo)
                                              .param("previousPage", previousPage)
                                              .param("id", worker.getId().toString()))
               .andExpect(MockMvcResultMatchers.forwardedUrl(expectedUrl))
               .andExpect(MockMvcResultMatchers.model().attribute("previousPage", previousPage))
               .andExpect(MockMvcResultMatchers.model().attribute("worker", worker));

    }

    //#endregion

    @Test
    public void updateWorkerCall() throws Exception {

        Worker worker = workerBuilder.createWithEverything(company.getId()).build().addDepartment(department);
        worker = workerDAOForTesting.persistAndReturnPersisted(worker);

        String goTo = "/" + controllerPath + "/edit";
        String expectedUrl = "/1";

        String previousPage = ",/rhsoft" + expectedUrl;

        mockMvc.perform(MockMvcRequestBuilders.post(goTo)
                                              .param("previousPage", previousPage)
                                              .param("id", "1")
                                              .flashAttr("worker", worker))
               .andExpect(MockMvcResultMatchers.redirectedUrl(expectedUrl));

    }

}