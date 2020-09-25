package br.com.gabriel.rhsoft.models.previouspages;

public class WorkerList implements PreviousPage {

    private String path;

    public WorkerList(String path){
        if(path.contains("rhsoft")){
            this.path = path.split("/rhsoft")[1];
        }else{
            this.path = path;
        }
    }

    @Override
    public String getPath() {
        return this.path;
    }
    
}