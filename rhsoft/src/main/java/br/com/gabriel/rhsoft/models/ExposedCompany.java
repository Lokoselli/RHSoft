package br.com.gabriel.rhsoft.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ExposedCompany {

    private Company company;

    public List<Department> getDepartments(){
        if(this.company == null){
            return new ArrayList<>();
        }
        return this.company.getDepartments();
    }

    public Integer getExposedId(){
        return this.company.getId();
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