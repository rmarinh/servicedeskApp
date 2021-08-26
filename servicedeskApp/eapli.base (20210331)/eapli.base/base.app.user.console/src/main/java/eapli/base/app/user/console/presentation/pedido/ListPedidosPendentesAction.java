package eapli.base.app.user.console.presentation.pedido;

import eapli.framework.actions.Action;

public class ListPedidosPendentesAction implements Action {

    @Override
    public boolean execute() {
        return new ListPedidosPendentesUI().show();
    }


}
