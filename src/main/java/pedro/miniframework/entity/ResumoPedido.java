package pedro.miniframework.entity;

import java.math.BigDecimal;

public class ResumoPedido {
    private final Pedido pedido;
    private final BigDecimal totalBruto;
    private final BigDecimal desconto;
    private final BigDecimal totalLiquido;

    private ResumoPedido(Builder builder) {
        this.pedido = builder.pedido;
        this.totalBruto = builder.totalBruto;
        this.desconto = builder.desconto;
        this.totalLiquido = builder.totalLiquido;
    }

    public static class Builder {
        private Pedido pedido;
        private BigDecimal totalBruto;
        private BigDecimal desconto;
        private BigDecimal totalLiquido;

        public Builder pedido(Pedido pedido) {
            this.pedido = pedido;
            return this;
        }

        public Builder totalBruto(BigDecimal totalBruto) {
            this.totalBruto = totalBruto;
            return this;
        }

        public Builder desconto(BigDecimal desconto) {
            this.desconto = desconto;
            return this;
        }

        public Builder totalLiquido(BigDecimal totalLiquido) {
            this.totalLiquido = totalLiquido;
            return this;
        }

        public ResumoPedido build() {
            return new ResumoPedido(this);
        }
    }

    public Pedido getPedido() {
        return pedido;
    }

    public BigDecimal getTotalBruto() {
        return totalBruto;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public BigDecimal getTotalLiquido() {
        return totalLiquido;
    }
}