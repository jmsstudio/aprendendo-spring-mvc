package br.com.jmsstudio.repository;

import br.com.jmsstudio.builder.ProdutoBuilder;
import br.com.jmsstudio.config.JPAConfig;
import br.com.jmsstudio.config.DataSourceConfigTest;
import br.com.jmsstudio.model.Produto;
import br.com.jmsstudio.model.TipoPreco;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfig.class, ProdutoRepository.class, DataSourceConfigTest.class})
@ActiveProfiles("test")
public class ProdutoRepositoryTest {

    @Autowired
    ProdutoRepository produtoRepository;

    @Test
    public void deveSomarOsPrecosDosProdutosPorTipo() {
        List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, new BigDecimal(49.9)).more(5).buildAll();
        List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, new BigDecimal(19.9)).more(2).buildAll();

        livrosImpressos.stream().forEach(produtoRepository::save);
        livrosEbook.stream().forEach(produtoRepository::save);

        BigDecimal totalImpressos = produtoRepository.somaPrecosPorTipo(TipoPreco.IMPRESSO);
        BigDecimal totalEbook = produtoRepository.somaPrecosPorTipo(TipoPreco.EBOOK);

        Assert.assertEquals("Total errado", new BigDecimal(299.40).round(MathContext.DECIMAL32).setScale(2), totalImpressos.setScale(2));
        Assert.assertEquals("Total errado", new BigDecimal(59.70).round(MathContext.DECIMAL32).setScale(2), totalEbook.setScale(2));
    }
}
