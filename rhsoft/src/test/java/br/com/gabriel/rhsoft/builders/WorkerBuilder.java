package br.com.gabriel.rhsoft.builders;

import br.com.gabriel.rhsoft.models.Worker;

public class WorkerBuilder {

    Worker worker = new Worker();

    public WorkerBuilder createWithEverything(Integer companyId){

        worker.setEmail("emailteste@gmail.com");
        worker.setPersonalId("111111");
        worker.setName("Teste Worker");

        return this;
    }

    public Worker build(){
        return this.worker;
    }
    
}