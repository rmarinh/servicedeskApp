package eapli.base.app.user.console.presentation.pedido;

import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListPedidosPendentesAssignedToColaboradorUI extends AbstractListUI<Pedido> {

    private ListPedidoController theController = new ListPedidoController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final Username user = authz.session().get().authenticatedUser().identity();


    @Override
    public String headline() {
        return "Lista dos Pedidos Pendentes Assignados existentes em sistema:";
    }

    @Override
    protected String emptyMessage() {
        return "Não existem pedidos!";
    }

    @Override
    protected Iterable<Pedido> elements() {
        return theController.allPedidosPendentesAssignedToColaborador(user);
    }

    @Override
    protected Visitor<Pedido> elementPrinter() {
        return new PedidoPrinter();
    }

    @Override
    protected String elementName() {
        return "Pedidos Pendentes Assignados";
    }

    @Override
    protected String listHeader() {
        return String.format("#  %-10s%-40s%-10s%-10s", "ID", "Token", "Serviço", "Estado");
    }
}
