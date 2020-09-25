package br.com.gabriel.rhsoft.models.previouspages;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class PreviousPageInfo{

    PreviousPage previousPage;

    public PreviousPage getPreviousPageAndNull(){
        PreviousPage previousPage = this.previousPage;
        this.previousPage = null;
        return previousPage;
    }

    public PreviousPage getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(PreviousPage previousPage) {
        this.previousPage = previousPage;
    }

    
}