package eapli.base.app.servicosrh.console.presentation.equipa;

import eapli.framework.actions.Action;

public class RemoverColaboradorEquipaAction implements Action {

    @Override
    public boolean execute() {
        return new RemoverColaboradorEquipaUI().show();
    }


}
