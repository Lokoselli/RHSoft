package br.com.gabriel.rhsoft.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.gabriel.rhsoft.daos.DepartmentsDAO;
import br.com.gabriel.rhsoft.daos.WorkersDAO;
import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.Worker;
import br.com.gabriel.rhsoft.models.previouspages.ListToAdd;
import br.com.gabriel.rhsoft.models.previouspages.PreviousPage;
import br.com.gabriel.rhsoft.models.previouspages.PreviousPageInfo;
import br.com.gabriel.rhsoft.models.previouspages.WorkerList;

@Controller
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    PreviousPageInfo previousPageInfo;

    @Autowired
    WorkersDAO workersDAO;

    @Autowired
    DepartmentsDAO departmentsDAO;

    @RequestMapping(value = "/form", name = "workerForm")
    public ModelAndView workerForm(PreviousPageInfo previousPage) {
        ModelAndView modelAndView = new ModelAndView("workers/workerForm");

        return modelAndView;
    }

    @RequestMapping(value = "/edit", name = "workerEditForm")
    public ModelAndView workerEditForm(String previousPage,Integer id){
        ModelAndView modelAndView = new ModelAndView("workers/workerEditForm");

        Worker worker = workersDAO.findById(id);
        modelAndView.addObject("worker", worker);
        modelAndView.addObject("previousPage", previousPage);

        return modelAndView;
    }

    @RequestMapping(value = "/update", name = "updateWorker", method = RequestMethod.POST)
    public String updateWorker(Worker worker, String previousPage){

        String redirectTo = previousPage.split(",/rhsoft")[1];
        System.out.println(worker);
        workersDAO.editWorker(worker);

        return "redirect:" + redirectTo;
    }

    @RequestMapping(value = "/list", name = "listAllWorkers")
    public ModelAndView listAllWorkers(HttpServletRequest request, HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/workers/detail");

        WorkerList workerList = new WorkerList(request.getRequestURI());
        previousPageInfo.setPreviousPage(workerList);

        modelAndView.addObject("workers", workersDAO.getAllWorkers(getExposedCompany(session)));
        modelAndView.addObject("previousPage", request.getRequestURI());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, name = "createWorker")
    public String createWorker(Worker worker, HttpSession session){

        workersDAO.persistWorker(worker, getExposedCompany(session));
    
        PreviousPage previousPage = previousPageInfo.getPreviousPageAndNull();
        
        return "redirect:" + previousPage.getPath();
    }

    @RequestMapping(value = "/delete", name = "deleteWorker")
    public String deleteWorker(Integer id){
        workersDAO.delete(id);

        return "redirect:/workers/list";
    }

    @RequestMapping(method = RequestMethod.GET, name = "addWorker", value = "listToAdd")
    public ModelAndView addWorkerList(Integer departmentId, HttpServletRequest request, HttpSession session){
        ModelAndView modelAndView = new ModelAndView("/workers/listToAdd");

        List<Worker> allWorkers = workersDAO.getAllWorkers(getExposedCompany(session));
        ListToAdd lToAdd= new ListToAdd(request.getRequestURI(), departmentId);
        previousPageInfo.setPreviousPage(lToAdd);

        modelAndView.addObject("workers", allWorkers);
        modelAndView.addObject("departmentId", departmentId);

        return modelAndView;
    }

    private Company getExposedCompany(HttpSession session){

        Company company = (Company)session.getAttribute("exposedCompany");
        return company;

    }
    
}