package br.com.jmsstudio.repository;

import br.com.jmsstudio.model.Produto;
import br.com.jmsstudio.model.TipoPreco;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class ProdutoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @CacheEvict(value = "produtosHomeCache", allEntries = true)
    public void save(Produto produto) {
        entityManager.persist(produto);
    }

    public List<Produto> list() {
        TypedQuery<Produto> query = entityManager.createQuery("select p from Produto p", Produto.class);
        return query.getResultList();
    }

    public Produto findById(Long id) {
        TypedQuery<Produto> query = entityManager.createQuery("select p from Produto p join fetch p.precos where p.id = :id", Produto.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public BigDecimal somaPrecosPorTipo(TipoPreco tipoPreco){
        TypedQuery<BigDecimal> query = entityManager
                .createQuery("select sum(preco.valor) from Produto p join p.precos preco where preco.tipo = :tipoPreco", BigDecimal.class);
        query.setParameter("tipoPreco", tipoPreco);
        return query.getSingleResult();
    }
}
