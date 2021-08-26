package eapli.base.app.user.console.presentation.pedido;

import eapli.framework.actions.Action;

public class ListMeusPedidosEmCursoAction implements Action {
    @Override
    public boolean execute() {
        return new ListMeusPedidosEmCursoUI().show();
    }
}
