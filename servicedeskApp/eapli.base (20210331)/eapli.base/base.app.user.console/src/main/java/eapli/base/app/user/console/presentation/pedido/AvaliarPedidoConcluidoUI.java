package eapli.base.app.user.console.presentation.pedido;

import eapli.base.pedidomanagement.application.AvaliarPedidoController;
import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class AvaliarPedidoConcluidoUI extends AbstractUI {

    private final AvaliarPedidoController theController = new AvaliarPedidoController();
    private final ListPedidoController theControllerPedidos = new ListPedidoController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final Username user = authz.session().get().authenticatedUser().identity();

    @Override
    protected boolean doShow() {

        final Iterable<Pedido> pedidos = this.theControllerPedidos.allPedidosConcluidosCriadosPorColaborador(user);

        final SelectWidget<Pedido> selectorPedidos = new SelectWidget<>("Pedidos:", pedidos,
                new PedidoPrinter());

        if (selectorPedidos.selectedOption() == 0) {
            return false;
        }

        if (!pedidos.iterator().hasNext()) {
            System.out.println("Não há pedidos concluidos!");
            return false;
        }

        System.out.println("Escolha um pedido");
        selectorPedidos.show();

        final Pedido pedido = selectorPedidos.selectedElement();

        System.out.println("Avaliação pedido - " + pedido.identity() + "\n");

        String cotacao = Console.readLine("Insira a cotação (1-Muito Mau, 2-Mau, 3-Razoável, 4-Bom, 5-Muito Bom)");
        while (!(cotacao.equals("1") || cotacao.equals("2")
                || cotacao.equals("3") || cotacao.equals("4")
                || cotacao.equals("5"))) {
            System.out.println("Seleção errada! Insira novamente");
            cotacao = Console.readLine("Insira a cotação (1-Muito Mau, 2-Mau, 3-Razoável, 4-Bom, 5-Muito Bom)");
        }

        String opcao = Console.readLine("Pretende adicionar comentário? (y|n)");
        while (!(opcao.equals("y") || opcao.equals("n"))) {
            System.out.println("Seleção errada! Insira novamente");
            opcao = Console.readLine("Pretende adicionar comentário? (y|n)");
        }
        String comentario = null;

        if (opcao.equals("y")) {
            comentario = Console.readLine("Insira o comentário:");
        }

        theController.avaliarPedido(pedido, Integer.parseInt(cotacao), comentario);

        return false;
    }

    @Override
    public String headline() {
        return "Avaliar Pedido Concluido";
    }
}
