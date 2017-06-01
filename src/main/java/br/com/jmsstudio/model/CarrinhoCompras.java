package br.com.jmsstudio.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable {

    private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();

    public void add(CarrinhoItem item) {
        itens.put(item, getQuantidade(item) +1);
    }

    public int getQuantidade(CarrinhoItem item) {
        if (!itens.containsKey(item)) {
            itens.put(item, 0);
        }
        return itens.get(item);
    }

    public Collection<CarrinhoItem> getItens() {
        return itens.keySet();
    }

    public int getQuantidade() {
        return itens.values().stream().reduce(0, (item, total) -> item + total);
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (CarrinhoItem ci: itens.keySet()) {
            total = total.add(getTotal(ci));
        }

        return total;
    }

    public BigDecimal getTotal(CarrinhoItem item) {
        return item.getTotal(getQuantidade(item));
    }

    public void remove(Produto produto, TipoPreco tipoPreco) {
        CarrinhoItem item = new CarrinhoItem(produto, tipoPreco);
        itens.remove(item);
    }
}
