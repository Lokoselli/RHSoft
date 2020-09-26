package br.com.gabriel.rhsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.gabriel.rhsoft.daos.CompanyDAO;
import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.ExposedCompany;

@Controller
@RequestMapping("/")
public class CompanyControllers {

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private ExposedCompany exposedCompany;

    @RequestMapping("/create12345")
    public String companyForm() {
        return "company/companyForm";
    }

    @RequestMapping(method = RequestMethod.POST, name = "gravaEmpresa")
    public String createEmpresa(Company company) {

        companyDAO.persistCompany(company);
        return "redirect:/";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public String setExposedCompany(@PathVariable(name = "id") Integer id) {

        Company selected = companyDAO.findCompanyById(id);
        exposedCompany.setCompany(selected);

        return "redirect:/";
    }

}