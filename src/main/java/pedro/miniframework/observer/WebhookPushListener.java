package pedro.miniframework.observer;

import pedro.miniframework.entity.ResumoPedido;

public class WebhookPushListener implements ObservadorPedido {
    @Override
    public void aoProcessarPedido(ResumoPedido resumo) {
        enviarPush(resumo);
    }

    public void enviarPush(ResumoPedido resumo) {
        String jsonPayload = """
                [Webhook POST -> api.pagamentos.com/v1/notificacoes]
                {
                    "evento": "PEDIDO_CRIADO",
                    "status": "AGUARDANDO_PAGAMENTO",
                    "pedidoId": %d,
                    "valorCobrado": %,.2f,
                    "mensagemPush": "Seu pedido de R$ %,.2f aguarda pagamento!"
                }
                """.formatted(
                resumo.getPedido().getId(),
                resumo.getTotalLiquido(),
                resumo.getTotalLiquido()
        );

        System.out.println(jsonPayload);
    }
}