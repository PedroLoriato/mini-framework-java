package pedro.miniframework.strategy;

import java.math.BigDecimal;

public interface CalculadoraDesconto {
    BigDecimal calcular(BigDecimal preco);
}
