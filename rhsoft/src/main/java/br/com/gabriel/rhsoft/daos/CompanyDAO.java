package br.com.gabriel.rhsoft.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.gabriel.rhsoft.models.Company;

@Repository
@Transactional
public class CompanyDAO {

    @PersistenceContext
    private EntityManager manager;

    public void persistCompany(Company company){
        manager.persist(company);
    }

    public void updateCompany(Company company){
        manager.merge(company);
    }

    public Company findCompanyById(Integer id) {
        Company company = manager.createQuery("select c from Company c where id=:id", Company.class).setParameter("id", id).getSingleResult();

        return company;

    }
    
}