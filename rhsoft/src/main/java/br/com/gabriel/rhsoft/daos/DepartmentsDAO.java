package br.com.gabriel.rhsoft.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.gabriel.rhsoft.models.Department;

@Repository
@Transactional
public class DepartmentsDAO {
    
    @PersistenceContext
    private EntityManager manager;

    public void persistDepartment(Department department){
        manager.persist(department);
    }

}