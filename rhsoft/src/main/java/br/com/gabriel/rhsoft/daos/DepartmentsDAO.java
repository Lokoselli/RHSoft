package br.com.gabriel.rhsoft.daos;

import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.Department;
import br.com.gabriel.rhsoft.models.Worker;

@Repository
@Transactional
public class DepartmentsDAO {
    
    @PersistenceContext
    private EntityManager manager;

    public int persistDepartment(Department department){
        manager.persist(department);
        return department.getId();
    }

    public void editDepartment(Department department, Company exposedCompany){

        department.setCompany(exposedCompany);

        manager.merge(department);
    }

    public Department findById(Integer id){
        return manager.createQuery("select d from Department d where d.id=:id", Department.class).setParameter("id", id).getSingleResult();
    }

    public void deleteById(Integer departmentId, Integer companyId){
        Department departmentToDelete = manager.find(Department.class, departmentId);
        if(departmentToDelete.getCompany().getId().equals(companyId)){
            departmentToDelete.setWorkers(new HashSet<>());
        } else{
            return;
        }
        
        
    }

    public void addWorker(Integer workerId, Integer departmentId) {

        Worker workerToAdd = manager.find(Worker.class, workerId);
        Department department = manager.find(Department.class, departmentId);

        department.addWorker(workerToAdd);

    }

    public void removeDepartment(Integer workerId, Integer departmentId) {
        Worker workerToRemove = manager.find(Worker.class, workerId);
        Department department = manager.find(Department.class, departmentId);

        department.removeWorker(workerToRemove);
    }

}