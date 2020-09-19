package br.com.gabriel.rhsoft.models;

import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority{

    private static final long serialVersionUID = 1L;
    
	@Id
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
    
}
