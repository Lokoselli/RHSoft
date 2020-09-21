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

    public int persistDepartment(Department department){
        manager.persist(department);
        return department.getId();
    }

    public Department findById(Integer id){
        return manager.createQuery("select d from Department d where d.id=:id", Department.class).setParameter("id", id).getSingleResult();
    }

    public void deleteById(Integer id){
        Department departmentToDelete = this.findById(id);

        manager.remove(departmentToDelete);
    }

}