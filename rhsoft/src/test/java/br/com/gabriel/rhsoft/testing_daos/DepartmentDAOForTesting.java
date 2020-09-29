package br.com.gabriel.rhsoft.testing_daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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

    public Department findById(Integer id) {
        System.out.println("ponto 1" + manager.find(Department.class, id));
        return manager.find(Department.class, id);
    }

    public boolean checkIfDepartmentExists(Department department) {

        Department other = manager.find(Department.class, department.getId());

        return department.equals(other);
    }

    public Integer getDepartmentListSize() {
        return manager.createQuery("select d from Department d").getResultList().size();
    }

    public List<Department> getDepartmentList() {
        return manager.createQuery("select d from Department d", Department.class).getResultList();
    }

}