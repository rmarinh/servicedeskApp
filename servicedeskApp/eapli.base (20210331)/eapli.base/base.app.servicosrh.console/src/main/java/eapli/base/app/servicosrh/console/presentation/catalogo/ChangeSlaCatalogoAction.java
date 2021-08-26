package eapli.base.app.servicosrh.console.presentation.catalogo;

import eapli.framework.actions.Action;

/**
 * Menu action for changing SLA in a catalogo to the application.
 */
public class ChangeSlaCatalogoAction implements Action  {
    @Override
    public boolean execute() {
        return new ChangeSlaCatalogoUI().show();
    }
}
