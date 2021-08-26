package eapli.base.app.user.console.presentation.pedido;

import eapli.base.pedidomanagement.domain.Pedido;
import eapli.framework.visitor.Visitor;

public class PedidoPrinter implements Visitor<Pedido> {

    @Override
    public void visit(Pedido visitee) {
        System.out.printf("%-10s%-40s%-10s%-10s", visitee.identity(), visitee.token(), visitee.servico().identity(), visitee.status().toString());
    }
}
