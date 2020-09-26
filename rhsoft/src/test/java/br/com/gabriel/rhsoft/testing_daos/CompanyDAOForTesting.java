package br.com.gabriel.rhsoft.testing_daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.gabriel.rhsoft.models.Company;

@Repository
@Transactional
public class CompanyDAOForTesting {

    @PersistenceContext
    private EntityManager manager;

    public Company persistAndReturnPersisted(Company company){

        manager.persist(company);
        return manager.find(Company.class, company.getId());

    }
    
}