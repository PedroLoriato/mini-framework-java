package pedro.miniframework.entity;

import java.math.BigDecimal;

public class Produto {
    private final Integer id;
    private final String descricao;
    private final BigDecimal preco;

    private Produto(Builder builder) {
        this.id = builder.id;
        this.descricao = builder.descricao;
        this.preco = builder.preco;
    }

    public static class Builder {
        private Integer id;
        private String descricao;
        private BigDecimal preco;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder preco(BigDecimal preco) {
            this.preco = preco;
            return this;
        }

        public Produto build() {
            return new Produto(this);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}