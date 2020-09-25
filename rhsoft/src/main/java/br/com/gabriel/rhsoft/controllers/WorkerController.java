package br.com.gabriel.rhsoft.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/edit", name = "workerEditForm")
    public ModelAndView workerEditForm(String previousPage,Integer id){
        ModelAndView modelAndView = new ModelAndView("/workers/workerEditForm");

        Worker worker = workersDAO.findById(id);
        modelAndView.addObject("worker", worker);
        modelAndView.addObject("previousPage", previousPage);

        return modelAndView;
    }

    @RequestMapping(value = "/update", name = "updateWorker", method = RequestMethod.POST)
    public String updateWorker(Worker worker, String previousPage){

        String redirectTo = previousPage.split(",/rhsoft")[1];

        workersDAO.editWorker(worker);

        return "redirect:" + redirectTo;
    }

    @RequestMapping(value = "/list", name = "listAllWorkers")
    public ModelAndView listAllWorkers(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/workers/detail");

        modelAndView.addObject("workers", workersDAO.getAllWorkers());
        modelAndView.addObject("previousPage", request.getRequestURI());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, name = "createWorker")
    public String createWorker(Worker worker){

        if(exposedCompany.getCompany() == null){
            return "redirect:/";
        }

        workersDAO.persistWorker(worker);
        
        return "redirect:/" + exposedCompany.getCompany().getId();
    }

    @RequestMapping(value = "/delete", name = "deleteWorker")
    public String deleteWorker(Integer id){
        workersDAO.delete(id);

        return "redirect:/workers/list";
    }

    @RequestMapping(method = RequestMethod.GET, name = "addWorker", value = "listToAdd")
    public ModelAndView addWorkerList(Integer departmentId){
        ModelAndView modelAndView = new ModelAndView("/workers/listToAdd");

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