package br.com.gabriel.rhsoft.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.gabriel.rhsoft.models.Worker;

@Repository
@Transactional
public class WorkersDAO {
    
    @PersistenceContext
    private EntityManager manager;

    public void persistWorker(Worker worker){
        manager.persist(worker);
    }

}