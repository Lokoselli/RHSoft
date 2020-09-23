package br.com.gabriel.rhsoft.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gabriel.rhsoft.models.Department;
import br.com.gabriel.rhsoft.models.ExposedCompany;

@Repository
@Transactional
public class DepartmentsDAO {
    
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    ExposedCompany exposedCompany;

    public int persistDepartment(Department department){
        manager.persist(department);
        return department.getId();
    }

    public void editDepartment(Department department){

        department.setCompany(exposedCompany.getCompany());

        manager.merge(department);
    }

    public Department findById(Integer id){
        return manager.createQuery("select d from Department d where d.id=:id", Department.class).setParameter("id", id).getSingleResult();
    }

    public void deleteById(Integer id){
        Department departmentToDelete = this.findById(id);

        manager.remove(departmentToDelete);
    }

}