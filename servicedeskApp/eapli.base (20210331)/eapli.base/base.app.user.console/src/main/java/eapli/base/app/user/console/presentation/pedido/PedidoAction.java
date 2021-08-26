package eapli.base.app.user.console.presentation.pedido;

import eapli.base.app.user.console.presentation.userdashboard.UserDashboardActionUI;
import eapli.framework.actions.Action;

/**
 * Menu action for adding a new catalogo to the application.
 */
public class PedidoAction implements Action {

    @Override
    public boolean execute() {
        return new PedidoActionUI().show();
    }
}
