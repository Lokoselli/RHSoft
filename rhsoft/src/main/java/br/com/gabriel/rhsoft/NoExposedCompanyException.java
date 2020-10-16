package br.com.gabriel.rhsoft;

public class NoExposedCompanyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoExposedCompanyException(String errorMessage){
        super(errorMessage);
    }

    public NoExposedCompanyException(){
        super();
    }
    
}