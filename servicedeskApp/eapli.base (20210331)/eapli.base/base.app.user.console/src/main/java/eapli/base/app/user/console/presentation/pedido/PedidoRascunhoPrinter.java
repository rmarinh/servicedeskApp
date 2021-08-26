package eapli.base.app.user.console.presentation.pedido;

import eapli.base.pedidomanagement.dto.PedidoRascunhoDTO;
import eapli.framework.visitor.Visitor;

public class PedidoRascunhoPrinter implements Visitor<PedidoRascunhoDTO> {

    @Override
    public void visit(PedidoRascunhoDTO visitee) {
        System.out.printf("%-20%s-40%s-60%s", visitee.pk.toString(), visitee.idServico.toString(), visitee.urgencia.toString());
    }
}
