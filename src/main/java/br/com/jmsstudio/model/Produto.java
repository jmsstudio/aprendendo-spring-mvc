package br.com.jmsstudio.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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
    List<Preco> precos;

    @DateTimeFormat//(pattern = "dd/MM/yyyy")
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

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getPathSumario() {
        return pathSumario;
    }

    public void setPathSumario(String pathSumario) {
        this.pathSumario = pathSumario;
    }
}
