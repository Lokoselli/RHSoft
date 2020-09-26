package br.com.gabriel.rhsoft.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "company", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Department> departments = new ArrayList<>();

    public void addDepartment(Department department) {
        this.departments.add(department);
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public int getId() {
        return companyId;
    }

    public void setId(int id) {
        this.companyId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((departments == null) ? 0 : departments.hashCode()) + ((name == null) ? 0 : name.hashCode()) + (companyId);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Company other = (Company) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;

        if (companyId != other.companyId){
            return false;
        }
        return true;
    }

}