package br.com.gabriel.rhsoft.controllers;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.gabriel.rhsoft.builders.CompanyBuilder;
import br.com.gabriel.rhsoft.builders.DepartmentBuilder;
import br.com.gabriel.rhsoft.builders.WorkerBuilder;
import br.com.gabriel.rhsoft.conf.AppWebConfiguration;
import br.com.gabriel.rhsoft.conf.DataSourceConfigurationTest;
import br.com.gabriel.rhsoft.conf.JPAConfiguration;
import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.Department;
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
        this.workerBuilder = new WorkerBuilder();
        this.departmentBuilder = new DepartmentBuilder();
        this.companyBuilder = new CompanyBuilder();

        this.company = companyDAOForTesting.persistAndReturnPersisted(companyBuilder.newCompany().build());
        this.department = departmentDAOForTesting.persistAndReturnPersisted(departmentBuilder.buildBasic(this.company).build());


        this.session = new MockHttpSession();
        session.setAttribute("exposedCompany", company);

        
    }

}