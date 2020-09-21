package br.com.gabriel.rhsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String departmentForm() {
        return "/departments/departmentsForm";
    }

    @RequestMapping(method = RequestMethod.POST, name = "createDepartment")
    public String createDepartment(Department department) {

        if (exposedCompany.getCompany() == null) {

            return "redirect:/";

        }

        department.setCompany(exposedCompany.getCompany());
        departmentsDAO.persistDepartment(department);

        return "redirect:/company?id=" + exposedCompany.getCompany().getId();

    }

    @RequestMapping(name = "removeDepartment", value = "/remove/{id}", method = RequestMethod.POST)
    public String removeDepartment(@PathVariable(value = "id") Integer id) {

        if (exposedCompany.getCompany() == null) {
            return "redirect:/";
        }

        departmentsDAO.deleteById(id);

        return "redirect:/company?id=" + exposedCompany.getCompany().getId();
    }

}