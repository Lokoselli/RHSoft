package br.com.gabriel.rhsoft.testing_daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.gabriel.rhsoft.models.Worker;

@Repository
@Transactional
public class WorkerDAOForTesting {
    
    @PersistenceContext
    EntityManager manager;

    public Worker persistAndReturnPersisted(Worker worker){
        manager.persist(worker);
        return manager.find(Worker.class, worker.getId());
    }

}