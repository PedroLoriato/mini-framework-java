package pedro.miniframework.strategy;

import java.math.BigDecimal;

public class DescontoClientePrata implements CalculadoraDesconto {
    private static final BigDecimal TAXA_DESCONTO = new BigDecimal("0.10");

    @Override
    public BigDecimal calcular(BigDecimal preco) {
        final BigDecimal quantiaDesconto = preco.multiply(TAXA_DESCONTO);
        return preco.subtract(quantiaDesconto);
    }
}
