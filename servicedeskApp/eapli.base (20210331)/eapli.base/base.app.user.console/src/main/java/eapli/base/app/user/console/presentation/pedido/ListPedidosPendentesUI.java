package eapli.base.app.user.console.presentation.pedido;

import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.application.ListPedidoController;

import eapli.framework.visitor.Visitor;
import eapli.framework.presentation.console.AbstractListUI;

public class ListPedidosPendentesUI extends AbstractListUI<Pedido> {
    private ListPedidoController theController = new ListPedidoController();

    @Override
    public String headline() {
        return "Lista de Pedidos Pendentes existentes em sistema:";
    }

    @Override
    protected String emptyMessage() {
        return "Não existem pedidos pendentes disponiveis!";
    }

    @Override
    protected Iterable<Pedido> elements() {
        return theController.allPedidosPendentes();
    }

    @Override
    protected Visitor<Pedido> elementPrinter() {
        return new PedidoPrinter();
    }

    @Override
    protected String elementName() {
        return "Pedidos Pendentes";
    }

    @Override
    protected String listHeader() {
        return String.format("#  %-10s%-40s%-10s%-10s", "ID", "Token", "Serviço", "Estado");
    }

}
