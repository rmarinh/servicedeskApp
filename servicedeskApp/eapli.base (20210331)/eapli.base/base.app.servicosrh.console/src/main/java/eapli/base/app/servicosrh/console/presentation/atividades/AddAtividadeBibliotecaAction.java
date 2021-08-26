package eapli.base.app.servicosrh.console.presentation.atividades;

import eapli.framework.actions.Action;

/**
 * Menu action for adding a new catalogo to the application.
 */
public class AddAtividadeBibliotecaAction implements Action {

    @Override
    public boolean execute() {
        return new AddAtividadeBibliotecaUI().show();
    }
}
