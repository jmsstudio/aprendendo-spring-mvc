package br.com.jmsstudio.repository;

import br.com.jmsstudio.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class UsuarioRepository implements UserDetailsService {

    @PersistenceContext
    private EntityManager entityManager;

    public Usuario findByCodigoLogin(String codigoLogin) {
        TypedQuery<Usuario> query = entityManager.createQuery("select u from Usuario u where u.codigoLogin = :codigologin", Usuario.class);
        query.setParameter("codigologin", codigoLogin);

        return query.getSingleResult();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return findByCodigoLogin(userName);
    }
}
