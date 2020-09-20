package br.com.gabriel.rhsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.gabriel.rhsoft.daos.CompanyDAO;
import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.Department;
import br.com.gabriel.rhsoft.models.ExposedCompany;

@Controller
@RequestMapping("/company")
public class CompanyControllers {

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private ExposedCompany exposedCompany;
    
    @RequestMapping("/create12345")
    public String createCompany(){
        return "/company/companyForm";
    }

    @RequestMapping(method = RequestMethod.POST, name = "gravaEmpresa")
    public String gravaEmpres(Company company){

        companyDAO.persistCompany(company);
        return "redirect:/";

    }

    @RequestMapping(method = RequestMethod.GET)
    public String setExposedCompany(Integer id){

        Company selected = companyDAO.findCompanyById(id);
        exposedCompany.setCompany(selected);

        return "redirect:/";
    }

    @RequestMapping("/addDepartment")
    public String addDepartment(Department department){

        exposedCompany.addDepartment(department);
        System.out.println(exposedCompany.getCompany().getId());
        companyDAO.updateCompany(exposedCompany.getCompany());

        return "redirect:/";
    }

}