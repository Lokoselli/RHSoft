package br.com.gabriel.rhsoft.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.gabriel.rhsoft.daos.CompanyDAO;
import br.com.gabriel.rhsoft.models.Company;

@Controller
@RequestMapping("/")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CompanyControllers {

    @Autowired
    private CompanyDAO companyDAO;

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
    public ModelAndView setExposedCompany(@PathVariable(name = "id") Integer id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("home");
        Company selected = companyDAO.findCompanyById(id);
        session.setAttribute("exposedCompany", selected);

        return modelAndView;
    }

}