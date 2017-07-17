package br.com.jmsstudio.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String titulo;
    String descricao;
    int paginas;

    @ElementCollection
    List<Preco> precos = new ArrayList<>();

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate dataLancamento;

    String pathSumario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Preco> getPrecos() {
        return precos;
    }

    public void setPrecos(List<Preco> precos) {
        this.precos = precos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public Date getDataLancamentoAsDate() {
        Date data = null;
        if (dataLancamento != null) {
            data = Date.from(dataLancamento.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        return data;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getPathSumario() {
        return pathSumario;
    }

    public void setPathSumario(String pathSumario) {
        this.pathSumario = pathSumario;
    }

    public BigDecimal getPreco(TipoPreco tipoPreco) {
        return precos.stream().filter(p -> p.getTipo().equals(tipoPreco)).findFirst().get().getValor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        return !(id != null ? !id.equals(produto.id) : produto.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
