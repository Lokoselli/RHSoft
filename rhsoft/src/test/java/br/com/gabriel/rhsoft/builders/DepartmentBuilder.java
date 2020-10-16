package br.com.gabriel.rhsoft.builders;

import java.util.HashSet;
import java.util.Set;

import br.com.gabriel.rhsoft.models.Company;
import br.com.gabriel.rhsoft.models.Department;
import br.com.gabriel.rhsoft.models.Worker;

public class DepartmentBuilder {

    private Department department = new Department();

    public DepartmentBuilder buildWithName(String name){
        this.department.setName(name);

        return this;
    }

    public DepartmentBuilder buildWithCompany(Company company){
        this.department.setCompany(company);

        return this;
    }

    public DepartmentBuilder buildWithWorkers(Set<Worker> workers){
        this.department.setWorkers(workers);

        return this;
    }

    public DepartmentBuilder buildBasic(Company company){

        this.buildWithName("Teste Department")
            .buildWithCompany(company)
            .buildWithWorkers(new HashSet<>());

        return this;
    }

    public DepartmentBuilder buildWithEverything(String name, Company company, Set<Worker> workers){
        
        this.buildWithName(name)
            .buildWithCompany(company)
            .buildWithWorkers(workers);
        
            return this;
    }

    public Department build() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    
    
}