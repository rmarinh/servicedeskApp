package eapli.base.app.servicosrh.console.presentation.atividades;

import eapli.base.app.servicosrh.console.presentation.catalogo.ListCatalogosUI;
import eapli.framework.actions.Action;

/**
 * @author
 */
public class ListAtividadesBibliotecaAction implements Action {

    @Override
    public boolean execute() {
        return new ListAtividadesBibliotecaUI().show();
    }
}

