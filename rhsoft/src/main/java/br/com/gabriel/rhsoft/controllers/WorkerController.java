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
import br.com.gabriel.rhsoft.models.previouspages.ListToAdd;
import br.com.gabriel.rhsoft.models.previouspages.PreviousPage;
import br.com.gabriel.rhsoft.models.previouspages.PreviousPageInfo;
import br.com.gabriel.rhsoft.models.previouspages.WorkerList;

@Controller
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    ExposedCompany exposedCompany;

    @Autowired
    PreviousPageInfo previousPageInfo;

    @Autowired
    WorkersDAO workersDAO;

    @Autowired
    DepartmentsDAO departmentsDAO;

    @RequestMapping(value = "/form", name = "workerForm")
    public ModelAndView workerForm(PreviousPageInfo previousPage) {
        ModelAndView modelAndView = new ModelAndView("/workers/workerForm");

        return modelAndView;
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

        WorkerList workerList = new WorkerList(request.getRequestURI());
        previousPageInfo.setPreviousPage(workerList);

        modelAndView.addObject("workers", workersDAO.getAllWorkers());
        modelAndView.addObject("previousPage", request.getRequestURI());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, name = "createWorker")
    public String createWorker(Worker worker){

        workersDAO.persistWorker(worker);
    
        PreviousPage previousPage = previousPageInfo.getPreviousPageAndNull();
        
        return "redirect:" + previousPage.getPath();
    }

    @RequestMapping(value = "/delete", name = "deleteWorker")
    public String deleteWorker(Integer id){
        workersDAO.delete(id);

        return "redirect:/workers/list";
    }

    @RequestMapping(method = RequestMethod.GET, name = "addWorker", value = "listToAdd")
    public ModelAndView addWorkerList(Integer departmentId, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("/workers/listToAdd");

        List<Worker> allWorkers = workersDAO.getAllWorkers();
        ListToAdd lToAdd= new ListToAdd(request.getRequestURI(), departmentId);
        previousPageInfo.setPreviousPage(lToAdd);

        modelAndView.addObject("workers", allWorkers);
        modelAndView.addObject("departmentId", departmentId);

        return modelAndView;
    }

    @RequestMapping(name = "teste", value = "/addDep")
    public String addDepartment(@RequestParam(value = "selected") Integer[] selected, @RequestParam(value = "departmentId") String departmentId){
        departmentId = departmentId.replace(",", "");
        
        for (Integer workerId : selected) {
            if(workerId == null){
                continue;
            }else{
                workersDAO.addDepartment(workerId, Integer.parseInt(departmentId));   
            }           
        }
        return "redirect:/departments/detail?id=" + departmentId ;
    }

    @RequestMapping(name = "workerRemoveDepartment", value = "/removeDep")
    public String removeDepartment(Integer workerId, Integer departmentId){

        workersDAO.removeDepartment(workerId, departmentId);

        return "redirect:/departments/detail?id=" + departmentId;
    }
    
}