package eapli.base.app.servicosrh.console.presentation.equipa;

import eapli.framework.actions.Action;

public class AddColaboradorToEquipaAction implements Action {

    @Override
    public boolean execute() {
        return new AddColaboradorToEquipaUI().show();
    }
}
