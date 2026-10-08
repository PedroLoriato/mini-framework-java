package pedro.miniframework;

import pedro.miniframework.entity.Pedido;
import pedro.miniframework.entity.Produto;
import pedro.miniframework.observer.WebhookPushListener;
import pedro.miniframework.service.EstoqueService;
import pedro.miniframework.strategy.DescontoClienteComum;
import pedro.miniframework.strategy.DescontoClienteOuro;
import pedro.miniframework.strategy.DescontoClientePrata;
import pedro.miniframework.strategy.DescontoClienteBronze;
import pedro.miniframework.template.ProcessadorPedido;
import pedro.miniframework.template.ProcessadorPedidoFisico;
import pedro.miniframework.template.ProcessadorPedidoDigital;
import pedro.miniframework.observer.EnviarEmailListener;
import pedro.miniframework.observer.EstoqueListener;
import pedro.miniframework.observer.AuditoriaListener;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Produto teclado = new Produto.Builder()
                .id(1)
                .descricao("Teclado Mecânico")
                .preco(new BigDecimal("250.00"))
                .build();

        Produto mouse = new Produto.Builder()
                .id(3)
                .descricao("Mouse Gamer")
                .preco(new BigDecimal("120.00"))
                .build();

        Produto licenca = new Produto.Builder()
                .id(99)
                .descricao("Licença Windows 11")
                .preco(new BigDecimal("800.00"))
                .build();

        Pedido pedido1 = new Pedido.Builder()
                .id(1001)
                .produto(teclado)
                .quantidade(2.0)
                .build();

        Pedido pedido2 = new Pedido.Builder()
                .id(1002)
                .produto(licenca)
                .quantidade(1.0)
                .build();

        Pedido pedido3 = new Pedido.Builder()
                .id(1003)
                .produto(mouse)
                .quantidade(5.0)
                .build();

        Pedido pedido4 = new Pedido.Builder()
                .id(1004)
                .produto(licenca)
                .quantidade(3.0)
                .build();

        Pedido pedido5 = new Pedido.Builder()
                .id(1005)
                .produto(teclado)
                .quantidade(50.0)
                .build();

        EstoqueService estoqueService = new EstoqueService();

        System.out.println("==================================================");
        System.out.println("CENÁRIO 1: Pedido Físico | Cliente Comum");
        System.out.println("==================================================");
        ProcessadorPedido procFisico = new ProcessadorPedidoFisico(new DescontoClienteComum(), estoqueService);
        procFisico.adicionarObservador(new EstoqueListener(estoqueService));
        procFisico.adicionarObservador(new EnviarEmailListener());
        procFisico.adicionarObservador(new AuditoriaListener());
        procFisico.processar(pedido1);

        System.out.println("\n==================================================");
        System.out.println("CENÁRIO 2: Pedido Digital | Cliente Ouro");
        System.out.println("==================================================");
        ProcessadorPedido procDigital = new ProcessadorPedidoDigital(new DescontoClienteOuro());
        procDigital.adicionarObservador(new EnviarEmailListener());
        procDigital.processar(pedido2);

        System.out.println("\n==================================================");
        System.out.println("CENÁRIO 3: Pedido Físico | Cliente Bronze");
        System.out.println("==================================================");
        ProcessadorPedido procFisicoBronze = new ProcessadorPedidoFisico(new DescontoClienteBronze(), estoqueService);
        procFisicoBronze.adicionarObservador(new EstoqueListener(estoqueService));
        procFisicoBronze.adicionarObservador(new AuditoriaListener());
        procFisicoBronze.processar(pedido3);

        System.out.println("\n==================================================");
        System.out.println("CENÁRIO 4: Pedido Digital | Cliente Prata");
        System.out.println("==================================================");
        ProcessadorPedido procDigitalPrata = new ProcessadorPedidoDigital(new DescontoClientePrata());
        procDigitalPrata.adicionarObservador(new EnviarEmailListener());
        procDigitalPrata.adicionarObservador(new AuditoriaListener());
        procDigitalPrata.adicionarObservador(new WebhookPushListener());
        procDigitalPrata.processar(pedido4);

        System.out.println("\n==================================================");
        System.out.println("CENÁRIO 5: Pedido Físico Falha | Cliente Ouro");
        System.out.println("==================================================");
        ProcessadorPedido procFisicoEstoque = new ProcessadorPedidoFisico(new DescontoClienteOuro(), estoqueService);
        procFisicoEstoque.adicionarObservador(new EstoqueListener(estoqueService));
        procFisicoEstoque.adicionarObservador(new EnviarEmailListener());
        procFisicoEstoque.adicionarObservador(new AuditoriaListener());
        procFisicoEstoque.processar(pedido5);
    }
}