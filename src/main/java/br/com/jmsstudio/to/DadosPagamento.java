package br.com.jmsstudio.to;

import java.math.BigDecimal;

public class DadosPagamento {

    private BigDecimal value;

    public DadosPagamento(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
