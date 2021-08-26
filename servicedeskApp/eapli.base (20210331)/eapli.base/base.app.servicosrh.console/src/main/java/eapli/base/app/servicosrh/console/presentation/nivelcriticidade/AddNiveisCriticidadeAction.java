package eapli.base.app.servicosrh.console.presentation.nivelcriticidade;


import eapli.base.slaservicemanagement.domain.dto.NiveisCriticidadeDTO;
import eapli.framework.actions.Action;


public class AddNiveisCriticidadeAction implements Action {

    @Override
    public boolean execute() {
        NiveisCriticidadeDTO ncDTO = new AddNiveisCriticidadeUI().show();

        return ncDTO != null;
    }
}
