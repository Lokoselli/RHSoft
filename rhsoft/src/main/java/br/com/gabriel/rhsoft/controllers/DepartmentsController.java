package br.com.gabriel.rhsoft.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.gabriel.rhsoft.NoExposedCompanyException;
import br.com.gabriel.rhsoft.daos.DepartmentsDAO;
import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.Department;

@Controller
@RequestMapping("/departments")
public class DepartmentsController {

    @Autowired
    private DepartmentsDAO departmentsDAO;

    //#region Forms

    @RequestMapping(value = "/form", name = "departmentForm")
    public String departmentForm(HttpSession session) {
        if(getExposedCompany(session) == null){
            throw new NoExposedCompanyException();
        }

        return "departments/departmentsForm";
    }

    @RequestMapping(value="/edit", name = "departmentEditForm", method = RequestMethod.POST)
    public ModelAndView departmentEditForm(Integer id, HttpSession session) {

        if(getExposedCompany(session) == null){
            throw new NoExposedCompanyException();
        }

        ModelAndView modelAndView = new ModelAndView("departments/departmentsEditForm");

        Department department = departmentsDAO.findById(id);
        modelAndView.addObject("department", department);

        return modelAndView;

    }

    //#endregion

    //#region CRUD
    @RequestMapping(method = RequestMethod.POST, name = "createDepartment")
    public String createDepartment(Department department, HttpSession session) {

        if(getExposedCompany(session) == null){
            throw new NoExposedCompanyException();
        }

        department.setCompany(getExposedCompany(session));        
        departmentsDAO.persistDepartment(department);

        return "redirect:" + urlUpdateExposed(session);

    }

    @RequestMapping(name = "removeDepartment", value = "/remove/{id}", method = RequestMethod.POST)
    public String removeDepartment(@PathVariable(value = "id") Integer id,  HttpSession session) {

        if(getExposedCompany(session) == null){
            throw new NoExposedCompanyException();
        }


        Company company = getExposedCompany(session);
        departmentsDAO.deleteById(id, company.getId());

        return "redirect:" + urlUpdateExposed(session);
    }

    @RequestMapping(value = "/editDepartment", name = "editDepartment", method = RequestMethod.POST)
    public String editDepartment(Department department, HttpSession session) {

        if(getExposedCompany(session) == null){
            throw new NoExposedCompanyException("No Exposed Company");
        }
        
        departmentsDAO.editDepartment(department, getExposedCompany(session));
        
        return("redirect:" + urlUpdateExposed(session));
    }

    @RequestMapping(value = "/detail", name = "detailDepartment")
    public ModelAndView detailDepartment(Integer id, HttpSession session) {
        if(getExposedCompany(session) == null){
            throw new NoExposedCompanyException("No Exposed Company");
        }

        ModelAndView modelAndView = new ModelAndView("/departments/detail");
        Department department = departmentsDAO.findById(id);

        modelAndView.addObject("department", department);

        return modelAndView;
    }

    //#endregion

    @RequestMapping(value = "/addWorker", name = "addWorkerToDep")
    public String addWorker(@RequestParam(value = "selected") Integer[] selected, @RequestParam(value = "departmentId") String departmentId){
        departmentId = departmentId.replace(",", "");
        
        for (Integer workerId : selected) {
            if(workerId == null){
                continue;
            }else{
                departmentsDAO.addWorker(workerId, Integer.parseInt(departmentId));   
            }           
        }

        return "redirect:/departments/detail?id=" + departmentId ;
    }

    @RequestMapping(name = "departmentRemoveWorker", value = "/removeWorker")
    public String removeDepartment(Integer workerId, Integer departmentId){

        departmentsDAO.removeDepartment(workerId, departmentId);

        return "redirect:/departments/detail?id=" + departmentId;
    }

    private String urlUpdateExposed(HttpSession session){
        return "/" + getExposedCompany(session).getId();
    }

    private Company getExposedCompany(HttpSession session){
        try{
            Company company = (Company)session.getAttribute("exposedCompany");
            return company;
        }catch(Exception e){
            return null;
        }
        
    }


}