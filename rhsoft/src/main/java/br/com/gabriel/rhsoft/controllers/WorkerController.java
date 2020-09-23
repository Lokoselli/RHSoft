package br.com.gabriel.rhsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.gabriel.rhsoft.daos.WorkersDAO;
import br.com.gabriel.rhsoft.models.ExposedCompany;
import br.com.gabriel.rhsoft.models.Worker;

@Controller
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    ExposedCompany exposedCompany;

    @Autowired
    WorkersDAO workersDAO;

    @RequestMapping(value = "/form", name = "workerForm")
    public String workerForm() {
        return "/workers/workerForm";
    }

    @RequestMapping(method = RequestMethod.POST, name = "createWorker")
    public String createWorker(Worker worker){

        if(exposedCompany.getCompany() == null){
            return "redirect:/";
        }

        workersDAO.persistWorker(worker);
        
        return "redirect:/" + exposedCompany.getCompany().getId();
    }
    
}