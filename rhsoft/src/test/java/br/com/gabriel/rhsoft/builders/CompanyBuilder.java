package br.com.gabriel.rhsoft.builders;

import br.com.gabriel.rhsoft.models.Company;


public class CompanyBuilder {

    private Company company = new Company();

    public CompanyBuilder newCompany(){
        this.company.setName("teste");

        return this;
    }

    public CompanyBuilder buildWithId(int companyId){
        
        this.company.setId(companyId);

        return this;
    }

    public CompanyBuilder buildWithName(String name){
        this.company.setName(name);

        return this;
    }

    public CompanyBuilder buildEverything(int companyId, String name){
        this.company.setName(name);
        this.company.setId(companyId);

        return this;
    }

    public Company build() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }



}