package br.com.gabriel.rhsoft.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.gabriel.rhsoft.models.Usuario;

public class UsuarioDAO implements UserDetailsService {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Usuario loadUserByUsername(String login) throws UsernameNotFoundException {
        List<Usuario> usuarios = manager.createQuery("select u from Usuario u where u.login=:login", Usuario.class)
        .setParameter("login", login).getResultList();

        if (usuarios.isEmpty()) {
            throw new UsernameNotFoundException("Usuário " + login + " não encontrado");
        }

        return usuarios.get(0);
    }

}
