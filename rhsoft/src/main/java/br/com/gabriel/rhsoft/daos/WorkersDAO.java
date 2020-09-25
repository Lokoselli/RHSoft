package br.com.gabriel.rhsoft.daos;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.gabriel.rhsoft.models.Department;
import br.com.gabriel.rhsoft.models.ExposedCompany;
import br.com.gabriel.rhsoft.models.Worker;

@Repository
@Transactional
public class WorkersDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    ExposedCompany exposedCompany;

    public void persistWorker(Worker worker) {
        worker.setCompanyId(exposedCompany.getExposedId());
        manager.persist(worker);
    }

    public List<Worker> getAllWorkers() {

        return manager.createQuery("select w from Worker w where w.companyId=:companyId", Worker.class).setParameter("companyId", exposedCompany.getExposedId()).getResultList();

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
        workerToDelete.setDepartments(new HashSet<>());

        manager.remove(workerToDelete);

    }

    public void editWorker(Worker worker) {
        Worker persistedWorker = manager.find(Worker.class, worker.getId());

        worker.setCompanyId(persistedWorker.getCompanyId());
        worker.setDepartments(persistedWorker.getDepartments());

        manager.merge(worker);

    }

}