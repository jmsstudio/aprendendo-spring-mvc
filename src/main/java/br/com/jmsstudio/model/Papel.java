package br.com.jmsstudio.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author jefferson.souza
 */
@Entity
public class Papel implements GrantedAuthority {

    @Id
    private String nome;

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
