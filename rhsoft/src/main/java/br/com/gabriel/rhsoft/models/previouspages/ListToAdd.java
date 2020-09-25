package br.com.gabriel.rhsoft.models.previouspages;

public class ListToAdd implements PreviousPage {

    private String path;
    private Integer departmentId;

    public ListToAdd(String path, Integer departmentId) {
        if(path.contains("rhsoft")){
            this.path = path.split("/rhsoft")[1];
        }else{
            this.path = path;
        }
        this.departmentId = departmentId;
    }

    @Override
    public String getPath() {

        return this.path + "?departmentId=" + this.departmentId;
        
    }
    
}