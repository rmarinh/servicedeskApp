package eapli.base.app.user.console.presentation.pedido;

import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.pedidomanagement.application.AssignarPedidoController;
import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.domain.PedidoStatus;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.visitor.Visitor;

public class AssignarPedidoPendenteUI extends AbstractUI {

    private ListPedidoController theController = new ListPedidoController();
    private final AssignarPedidoController theControllerAssignar = new AssignarPedidoController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final Username user = authz.session().get().authenticatedUser().identity();
    private final ListColaboradorService service = new ListColaboradorService();

    @Override
    protected boolean doShow() {

        Colaborador colaborador = service.findColaboradorByUsername(user).get();
        final Iterable<Pedido> pedidos = this.theController.allPedidosPendentes();

        final SelectWidget<Pedido> selectorPedidos = new SelectWidget<>("Pedidos pendentes:", pedidos,
                new PedidoPrinter());

        if (!pedidos.iterator().hasNext()) {
            System.out.println("Não há pedidos pendentes!");
            return false;
        }

        System.out.println("Escolha um pedido");
        selectorPedidos.show();

        if (selectorPedidos.selectedOption() == 0) {
            return false;
        }

        final Pedido pedido = selectorPedidos.selectedElement();

        if (pedido.status() == PedidoStatus.SUBMETIDO) {
            theControllerAssignar.assignarPedidoSubmetido(pedido, colaborador);
        } else if (pedido.status() == PedidoStatus.APROVADO) {
            theControllerAssignar.assignarPedidoAprovado(pedido, colaborador);
        } else {
            return false;
        }

        System.out.println("Pedido assignado com sucesso!");
        return true;
    }

    @Override
    public String headline() {
        return "Assignar pedido";
    }
}
