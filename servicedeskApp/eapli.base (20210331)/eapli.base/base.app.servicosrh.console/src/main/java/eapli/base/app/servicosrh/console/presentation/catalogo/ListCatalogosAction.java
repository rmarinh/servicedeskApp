package eapli.base.app.servicosrh.console.presentation.catalogo;

import eapli.framework.actions.Action;

/**
 *
 * @author
 */
public class ListCatalogosAction implements Action {

    @Override
    public boolean execute() {
        return new ListCatalogosUI().show();
    }
}

