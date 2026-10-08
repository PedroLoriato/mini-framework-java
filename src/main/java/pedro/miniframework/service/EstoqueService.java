package pedro.miniframework.service;

import pedro.miniframework.entity.Pedido;

import java.util.HashMap;
import java.util.Map;

public class EstoqueService {
    private static final Map<Integer, Double> estoqueLocal = new HashMap<>();

    static {
        estoqueLocal.put(1, 50.0);
        estoqueLocal.put(3, 200.0);
    }

    public boolean verificarDisponibilidade(Pedido pedido) {
        Integer idProduto = pedido.getProduto().getId();
        Double estoqueAtual = estoqueLocal.getOrDefault(idProduto, 0.0);
        return estoqueAtual >= pedido.getQuantidade();
    }

    public void darBaixa(Pedido pedido) {
        Integer idProduto = pedido.getProduto().getId();
        Double estoqueAtual = estoqueLocal.getOrDefault(idProduto, 0.0);

        estoqueLocal.put(idProduto, estoqueAtual - pedido.getQuantidade());
    }
}