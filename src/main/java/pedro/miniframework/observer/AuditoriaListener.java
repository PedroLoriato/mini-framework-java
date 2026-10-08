package pedro.miniframework.observer;

import pedro.miniframework.entity.ResumoPedido;

public class AuditoriaListener implements ObservadorPedido {
    @Override
    public void aoProcessarPedido(ResumoPedido resumo) {
        salvarLog(resumo);
    }

    public void salvarLog(ResumoPedido resumo) {
        String logAuditoria = """
                [Auditoria] Registro salvo em: %s | Pedido ID: %d | Valor Processado: R$ %,.2f\
                """.formatted(
                java.time.LocalDateTime.now(),
                resumo.getPedido().getId(),
                resumo.getTotalLiquido()
        );

        System.out.println(logAuditoria);
    }
}