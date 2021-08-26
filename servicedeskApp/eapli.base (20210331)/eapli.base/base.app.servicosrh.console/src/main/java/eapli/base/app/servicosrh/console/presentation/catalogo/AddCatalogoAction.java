package eapli.base.app.servicosrh.console.presentation.catalogo;

import eapli.framework.actions.Action;

/**
 * Menu action for adding a new catalogo to the application.
 */
public class AddCatalogoAction implements Action {

    @Override
    public boolean execute() {
        return new AddCatalogoUI().show();
    }
}
