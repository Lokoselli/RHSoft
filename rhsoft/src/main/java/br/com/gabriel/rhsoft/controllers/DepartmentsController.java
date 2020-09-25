package br.com.gabriel.rhsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.gabriel.rhsoft.daos.DepartmentsDAO;
import br.com.gabriel.rhsoft.models.Department;
import br.com.gabriel.rhsoft.models.ExposedCompany;

@Controller
@RequestMapping("/departments")
public class DepartmentsController {

    @Autowired
    private DepartmentsDAO departmentsDAO;

    @Autowired
    private ExposedCompany exposedCompany;

    //#region Forms

    @RequestMapping(value = "/form", name = "departmentForm")
    public String departmentForm() {
        return "/departments/departmentsForm";
    }

    @RequestMapping(value="/edit", name = "departmentEditForm", method = RequestMethod.POST)
    public ModelAndView departmentEditForm(Integer id){
        ModelAndView modelAndView = new ModelAndView("/departments/departmentsEditForm");

        Department department = departmentsDAO.findById(id);
        modelAndView.addObject("department", department);

        return modelAndView;

    }

    //#endregion

    //#region CRUD
    @RequestMapping(method = RequestMethod.POST, name = "createDepartment")
    public String createDepartment(Department department) {

        if (exposedCompany.getCompany() == null) {

            return "redirect:/";

        }

        department.setCompany(exposedCompany.getCompany());
        departmentsDAO.persistDepartment(department);

        return "redirect:" + urlUpdateExposed();

    }

    @RequestMapping(name = "removeDepartment", value = "/remove/{id}", method = RequestMethod.POST)
    public String removeDepartment(@PathVariable(value = "id") Integer id) {

        if (exposedCompany.getCompany() == null) {
            return "redirect:/";
        }

        departmentsDAO.deleteById(id);

        return "redirect:" + urlUpdateExposed();
    }

    @RequestMapping(value = "/editDepartment", name = "editDepartment", method = RequestMethod.POST)
    public String editDepartment(Department department) {
        
        departmentsDAO.editDepartment(department);

        return("redirect:" + urlUpdateExposed());
    }

    @RequestMapping(value = "/detail", name = "detailDepartment")
    public ModelAndView detailDepartment(Integer id){
        ModelAndView modelAndView = new ModelAndView("/departments/detail");
        Department department = departmentsDAO.findById(id);

        modelAndView.addObject("department", department);

        return modelAndView;
    }

    //#endregion

    private String urlUpdateExposed(){
        return "/" + exposedCompany.getCompany().getId();
    }


}