package br.com.gabriel.rhsoft.daos;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.Department;
import br.com.gabriel.rhsoft.models.Worker;

@Repository
@Transactional
public class WorkersDAO {

    @PersistenceContext
    private EntityManager manager;

    public void persistWorker(Worker worker, Company exposedCompany) {
        worker.setCompanyId(exposedCompany.getId());
        manager.persist(worker);
    }

    public List<Worker> getAllWorkers(Company exposedCompany) {

        return manager.createQuery("select w from Worker w where w.companyId=:companyId", Worker.class).setParameter("companyId", exposedCompany.getId()).getResultList();

    }

    public Worker findById(Integer workerId) {
        return manager.find(Worker.class, workerId);
    }

    public void addDepartment(Integer workerId, Integer departmentId) {
        
        Department department = manager.find(Department.class, departmentId);
        Worker selected = manager.find(Worker.class, workerId);
        selected.addDepartment(department);

    }

    public void delete(Integer id) {

        Worker workerToDelete = manager.find(Worker.class, id);
        Set<Department> departments = workerToDelete.getDepartments();
        for (Department department : departments) {
            department.removeWorker(workerToDelete);            
        }
        workerToDelete.nullDeparments();
        
        manager.remove(workerToDelete);

    }

    public void editWorker(Worker worker) {
        Worker persistedWorker = manager.find(Worker.class, worker.getId());

        worker.setCompanyId(persistedWorker.getCompanyId());
        worker.setDepartments(persistedWorker.getDepartments());

        manager.merge(worker);

    }

    public void removeDepartment(Integer workerId, Integer departmentId) {

        Worker worker = manager.find(Worker.class, workerId);
        Department department = manager.find(Department.class, departmentId);

        worker.removeDeparment(department);

    }

}