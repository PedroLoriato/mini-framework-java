package pedro.miniframework.observer;

import pedro.miniframework.entity.ResumoPedido;
import pedro.miniframework.entity.Pedido;

public class EnviarEmailListener implements ObservadorPedido {
    @Override
    public void aoProcessarPedido(ResumoPedido resumo) {
        enviarEmail(resumo);
    }

    public void enviarEmail(ResumoPedido resumo) {
        Pedido pedido = resumo.getPedido();
        String corpoEmail = """
                [E-mail] Enviando confirmação do pedido ID: %d
                [E-mail] Detalhes do seu pedido:
                --------------------------------------------------
                Produto: %s
                Quantidade: %.2f
                
                Resumo Financeiro:
                Subtotal: R$ %,.2f
                Desconto Aplicado: - R$ %,.2f
                --------------------------------------------------
                TOTAL FINAL A PAGAR: R$ %,.2f
                --------------------------------------------------
                Obrigado por comprar conosco!
                """.formatted(
                pedido.getId(),
                pedido.getProduto().getDescricao(),
                pedido.getQuantidade(),
                resumo.getTotalBruto(),
                resumo.getDesconto(),
                resumo.getTotalLiquido()
        );

        System.out.println(corpoEmail);
    }
}