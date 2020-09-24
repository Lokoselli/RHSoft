package br.com.gabriel.rhsoft.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.Department;

@Repository
@Transactional
public class SetupDAO {

    @PersistenceContext
    EntityManager manager;

    public Integer createEverything(Company company, Department department) {
        
        persistEverything(company, department);

        Department persistedDep = manager.find(Department.class, department.getId());
        Company persistedComp = manager.find(Company.class, company.getId());
        
        persistedDep.setCompany(persistedComp);
        persistedComp.getDepartments().add(persistedDep);

        return persistedComp.getId();
    }

    private void persistEverything(Company company, Department department){
        manager.persist(department);
        manager.persist(company);
    }
    
}