package br.com.gabriel.rhsoft.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.gabriel.rhsoft.models.Department;
import br.com.gabriel.rhsoft.models.Worker;

@Repository
@Transactional
public class WorkersDAO {

    @PersistenceContext
    private EntityManager manager;

    public void persistWorker(Worker worker) {
        manager.persist(worker);
    }

    public List<Worker> getAllWorkers() {

        return manager.createQuery("select w from Worker w", Worker.class).getResultList();

    }

    public Worker findById(Integer workerId) {
        return manager.find(Worker.class, workerId);
    }

    public void addDepartment(Integer workerId, Integer departmentId) {
        
        Department department = manager.find(Department.class, departmentId);
        Worker selected = manager.find(Worker.class, workerId);
        selected.addDepartment(department);

    }

}