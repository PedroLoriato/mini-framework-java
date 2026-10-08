package pedro.miniframework.template;

import pedro.miniframework.entity.Pedido;
import pedro.miniframework.strategy.CalculadoraDesconto;

import java.math.BigDecimal;

public class ProcessadorPedidoDigital extends ProcessadorPedido {
    public ProcessadorPedidoDigital(CalculadoraDesconto calculadora) {
        super(calculadora);
    }

    @Override
    protected void validar(Pedido pedido) {
        System.out.println(
                "[Validação] Pedido Digital: Verificando integridade do arquivo e links de DRM..."
        );
        if (pedido.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
    }

    @Override
    protected BigDecimal calcularTotal(Pedido pedido) {
        return pedido.subTotal();
    }
}