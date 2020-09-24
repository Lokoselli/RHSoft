package br.com.gabriel.rhsoft.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.gabriel.rhsoft.daos.DepartmentsDAO;
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

    @Autowired
    DepartmentsDAO departmentsDAO;

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

    @RequestMapping(method = RequestMethod.GET, name = "addWorker", value = "list")
    public ModelAndView addWorkerList(Integer departmentId){
        ModelAndView modelAndView = new ModelAndView("/workers/list");

        List<Worker> allWorkers = workersDAO.getAllWorkers();
        modelAndView.addObject("workers", allWorkers);
        modelAndView.addObject("departmentId", departmentId);

        return modelAndView;
    }

    @RequestMapping(name = "teste", value = "/teste")
    public String addDepartment(@RequestParam(value = "selected") Integer[] selected, @RequestParam(value = "departmentId") String departmentId){
        departmentId = departmentId.replace(",", "");
        
        for (Integer workerId : selected) {
            if(workerId == null){
                continue;
            }else{
                System.out.println(workerId);
                workersDAO.addDepartment(workerId, Integer.parseInt(departmentId));   
            }           
        }
        return "redirect:/departments/detail?id=" + departmentId ;
    }
    
}