/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.base.app.servicosrh.console.presentation.servicos;

import eapli.framework.actions.Action;

/**
 *
 * @author
 */
public class ListServicosIncompletosAction implements Action {

    @Override
    public boolean execute() {
        return new ListServicosIncompletosUI().show();
    }
}
