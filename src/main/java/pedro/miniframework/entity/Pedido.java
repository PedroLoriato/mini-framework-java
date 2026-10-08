package pedro.miniframework.entity;

import java.math.BigDecimal;

public class Pedido {
    private final Integer id;
    private final Produto produto;
    private final Double quantidade;

    private Pedido(Builder builder) {
        this.id = builder.id;
        this.produto = builder.produto;
        this.quantidade = builder.quantidade;
    }

    public BigDecimal subTotal() {
        return this.produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

    public static class Builder {
        private Integer id;
        private Produto produto;
        private Double quantidade;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder produto(Produto produto) {
            this.produto = produto;
            return this;
        }

        public Builder quantidade(Double quantidade) {
            this.quantidade = quantidade;
            return this;
        }

        public Pedido build() {
            return new Pedido(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }
}