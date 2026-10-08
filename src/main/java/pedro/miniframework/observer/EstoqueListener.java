package pedro.miniframework.observer;

import pedro.miniframework.entity.ResumoPedido;
import pedro.miniframework.service.EstoqueService;

public class EstoqueListener implements ObservadorPedido {
    private final EstoqueService estoqueService;

    public EstoqueListener(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @Override
    public void aoProcessarPedido(ResumoPedido resumo) {
        darBaixa(resumo);
    }

    public void darBaixa(ResumoPedido resumo) {
        Integer produtoId = resumo.getPedido().getProduto().getId();
        Double quantidade = resumo.getPedido().getQuantidade();
        estoqueService.darBaixa(resumo.getPedido());
        System.out.println("""
                [Estoque] Separação de %s unidade(s) do produto ID %d solicitada com sucesso.\
                """.formatted(quantidade, produtoId));
    }
}