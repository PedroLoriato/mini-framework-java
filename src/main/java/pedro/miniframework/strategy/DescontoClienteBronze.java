package pedro.miniframework.strategy;

import java.math.BigDecimal;

public class DescontoClienteBronze implements CalculadoraDesconto {
    private static final BigDecimal TAXA_DESCONTO = new BigDecimal("0.05");

    @Override
    public BigDecimal calcular(BigDecimal preco) {
        final BigDecimal quantiaDesconto = preco.multiply(TAXA_DESCONTO);
        return preco.subtract(quantiaDesconto);
    }
}
