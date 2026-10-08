package pedro.miniframework.template;

import pedro.miniframework.entity.Pedido;
import pedro.miniframework.entity.ResumoPedido;
import pedro.miniframework.observer.ObservadorPedido;
import pedro.miniframework.strategy.CalculadoraDesconto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class ProcessadorPedido {
    private final CalculadoraDesconto calculadoraDesconto;

    private final List<ObservadorPedido> observadores = new ArrayList<>();

    public ProcessadorPedido(CalculadoraDesconto calculadoraDesconto) {
        this.calculadoraDesconto = calculadoraDesconto;
    }

    public void adicionarObservador(ObservadorPedido observador) {
        this.observadores.add(observador);
    }

    public ResumoPedido criarResumoPedido(Pedido pedido, BigDecimal totalBruto) {
        BigDecimal totalLiquido = calculadoraDesconto.calcular(totalBruto);
        BigDecimal desconto = totalBruto.subtract(totalLiquido);

        return new ResumoPedido.Builder()
                .pedido(pedido)
                .totalBruto(totalBruto)
                .desconto(desconto)
                .totalLiquido(totalLiquido)
                .build();
    }

    public final void processar(Pedido pedido) {
        validar(pedido);
        BigDecimal totalBruto = calcularTotal(pedido);
        ResumoPedido resumoPedido = criarResumoPedido(pedido, totalBruto);
        notificar(resumoPedido);
    }

    protected abstract void validar(Pedido pedido);
    protected abstract BigDecimal calcularTotal(Pedido pedido);

    private void notificar(ResumoPedido resumo) {
        for (ObservadorPedido obs : observadores) {
            obs.aoProcessarPedido(resumo);
        }
    }

}
