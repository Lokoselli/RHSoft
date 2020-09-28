package br.com.gabriel.rhsoft.testing_daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.mysql.cj.xdevapi.Result;

import org.hibernate.QueryException;
import org.springframework.stereotype.Repository;

import br.com.gabriel.rhsoft.models.Department;

@Repository
@Transactional
public class DepartmentDAOForTesting {

    @PersistenceContext
    private EntityManager manager;

    public Department persistAndReturnPersisted(Department department) {

        manager.persist(department);
        return manager.find(Department.class, department.getId());

    }

    public Department findById(Integer id){
        return manager.find(Department.class, id);
    }

    public boolean checkIfDepartmentExists(Department department){

        Department other = manager.find(Department.class, department.getId());

        return department.equals(other);
    }

}