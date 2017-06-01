package br.com.jmsstudio.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CarrinhoItem implements Serializable {
    private Produto produto;

    private TipoPreco tipoPreco;

    public CarrinhoItem(Produto produto, TipoPreco tipoPreco) {
        this.produto = produto;
        this.tipoPreco = tipoPreco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public TipoPreco getTipoPreco() {
        return tipoPreco;
    }

    public void setTipoPreco(TipoPreco tipoPreco) {
        this.tipoPreco = tipoPreco;
    }

    public BigDecimal getPreco() {
        return produto.getPreco(tipoPreco);
    }

    public BigDecimal getTotal(int quantidade) {
        return getPreco().multiply(new BigDecimal(quantidade));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarrinhoItem that = (CarrinhoItem) o;

        if (produto != null ? !produto.equals(that.produto) : that.produto != null) return false;
        return tipoPreco == that.tipoPreco;

    }

    @Override
    public int hashCode() {
        int result = produto != null ? produto.hashCode() : 0;
        result = 31 * result + (tipoPreco != null ? tipoPreco.hashCode() : 0);
        return result;
    }
}
