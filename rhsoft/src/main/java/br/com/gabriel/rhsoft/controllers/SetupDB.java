package br.com.gabriel.rhsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gabriel.rhsoft.daos.SetupDAO;
import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.Department;

@Controller
public class SetupDB {

    @Autowired
    SetupDAO setupDAO;

    @RequestMapping("/createeverything123456")
    public String createSetup(){
        Company company = new Company();
        company.setName("Companhia Teste");

        Department department = new Department();
        department.setName("Departamento Teste");
        
        Integer idCreated = setupDAO.createEverything(company, department);

        return "redirect:/" + idCreated;
    }
    
}