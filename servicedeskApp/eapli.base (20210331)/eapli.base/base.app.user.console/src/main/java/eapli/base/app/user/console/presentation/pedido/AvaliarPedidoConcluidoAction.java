package eapli.base.app.user.console.presentation.pedido;

import eapli.framework.actions.Action;

public class AvaliarPedidoConcluidoAction implements Action {

    @Override
    public boolean execute() {
        return new AvaliarPedidoConcluidoUI().show();
    }


}
