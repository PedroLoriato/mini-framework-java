package pedro.miniframework.template;

import pedro.miniframework.entity.Pedido;
import pedro.miniframework.service.EstoqueService;
import pedro.miniframework.strategy.CalculadoraDesconto;

import java.math.BigDecimal;

public class ProcessadorPedidoFisico extends ProcessadorPedido {
    private final EstoqueService estoqueService;

    public ProcessadorPedidoFisico(
            CalculadoraDesconto calculadora,
            EstoqueService estoqueService
    ) {
        super(calculadora);
        this.estoqueService = estoqueService;
    }

    @Override
    protected void validar(Pedido pedido) {
        System.out.println("[Validação] Pedido Físico: Verificando estoque...");
        if (pedido.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (!estoqueService.verificarDisponibilidade(pedido)) {
            throw new IllegalStateException(
                    "Estoque insuficiente para o produto: "
                            + pedido.getProduto().getDescricao()
            );
        }
    }

    @Override
    protected BigDecimal calcularTotal(Pedido pedido) {
        BigDecimal frete = new BigDecimal("15.00");
        return pedido.subTotal().add(frete);
    }
}