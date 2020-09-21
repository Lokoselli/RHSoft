package br.com.gabriel.rhsoft.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class ExposedCompany {

    private Company company;

    public List<Department> getDepartments(){
        if(this.company == null){
            return new ArrayList<>();
        }
        return this.company.getDepartments();
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    
    @Override
    public String toString() {
        if(this.company == null){
            return "RHSoft";
        }
        return this.company.toString();
    }

}