package pedro.miniframework.observer;

import pedro.miniframework.entity.ResumoPedido;

public interface ObservadorPedido {
    void aoProcessarPedido(ResumoPedido resumo);
}
