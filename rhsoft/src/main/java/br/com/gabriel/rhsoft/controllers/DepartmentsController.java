package br.com.gabriel.rhsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/form", name = "departmentForm")
    public String departmentForm(){
        return "/departments/departmentsForm";
    }

    @RequestMapping(method = RequestMethod.POST, name = "createDepartment")
    public String createDepartment(Department department){
        
        if(exposedCompany.getCompany() == null){

            return "redirect:/";
            
        };

        departmentsDAO.persistDepartment(department);

        return "forward:/company/addDepartment";

    }
    
}